package com.hhub.reactiveApp;

import org.springframework.stereotype.Component;

import com.hhub.reactiveApp.document.Coffee;
import com.hhub.reactiveApp.repo.CoffeeRepository;

import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {
    private final CoffeeRepository repo;

    public DataLoader(CoffeeRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    private void load() {
        repo.deleteAll().thenMany(
                Flux.just("Kaldi's Coffee", "Philz Coffee", "Blue Bottle Coffee")
                        .map(Coffee::new)
                        .flatMap(repo::save))
                .thenMany(repo.findAll())
                .subscribe(System.out::println);
    }
}
