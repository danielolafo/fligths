package com.danielolafo.recommendation.Flights.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.danielolafo.recommendation.Flights.domain.entity.Airline;

import reactor.core.publisher.Mono;

@Repository
public interface AirlineRepository extends ReactiveCrudRepository<Airline, String> {

    Mono<Boolean> existsByAirlineCode(String airlineCode);
}