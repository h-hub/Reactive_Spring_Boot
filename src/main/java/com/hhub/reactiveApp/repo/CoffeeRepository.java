package com.hhub.reactiveApp.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.hhub.reactiveApp.document.Coffee;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, String> {
}