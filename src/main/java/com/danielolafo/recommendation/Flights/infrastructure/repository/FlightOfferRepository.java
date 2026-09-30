package com.danielolafo.recommendation.Flights.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.danielolafo.recommendation.Flights.domain.entity.FlightOffer;

import reactor.core.publisher.Flux;

@Repository
public interface FlightOfferRepository extends ReactiveCrudRepository<FlightOffer, Integer> {

    Flux<FlightOffer> findByFlightId(Integer flightId);

    Flux<FlightOffer> findByProviderName(String providerName);

    Flux<FlightOffer> findByCabinClassAndIsDealTrue(String cabinClass);

    Flux<FlightOffer> findByIsDealTrue();
}