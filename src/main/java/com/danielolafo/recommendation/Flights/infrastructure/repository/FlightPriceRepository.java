package com.danielolafo.recommendation.Flights.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.danielolafo.recommendation.Flights.domain.entity.FlightPrice;

import reactor.core.publisher.Flux;

@Repository
public interface FlightPriceRepository extends ReactiveCrudRepository<FlightPrice, Integer> {

    Flux<FlightPrice> findByOriginAndDestination(String origin, String destination);

    Flux<FlightPrice> findByOriginAndDestinationAndMonthNum(String origin, String destination, String monthNum);

    Flux<FlightPrice> findByOriginAndDestinationOrderByDayOfMonthAsc(String origin, String destination);
}