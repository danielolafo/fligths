package com.danielolafo.recommendation.Flights.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.danielolafo.recommendation.Flights.domain.entity.Airport;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AirportRepository extends ReactiveCrudRepository<Airport, String> {

    Flux<Airport> findByCountry(String country);

    Flux<Airport> findByCity(String city);

    Mono<Boolean> existsByAirportCode(String airportCode);
}