package com.hhub.reactiveApp.service;

import org.springframework.stereotype.Service;

import com.hhub.reactiveApp.document.Coffee;
import com.hhub.reactiveApp.model.CoffeeOrder;
import com.hhub.reactiveApp.repo.CoffeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Service
public class CoffeeService {
    private final CoffeeRepository repo;

    public CoffeeService(CoffeeRepository repo) {
        this.repo = repo;
    }

    public Flux<Coffee> getAllCoffees() {
        return repo.findAll();
    }

    public Mono<Coffee> getCoffeeById(String id) {
        return repo.findById(id);
    }

    public Flux<CoffeeOrder> getOrdersForCoffeeById(String coffeeId) {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(i -> new CoffeeOrder(coffeeId, Instant.now()));
    }
}