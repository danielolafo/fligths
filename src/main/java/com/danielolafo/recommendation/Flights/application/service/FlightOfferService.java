package com.danielolafo.recommendation.Flights.application.service;

import com.danielolafo.recommendation.Flights.application.dto.FlightOfferDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightOfferService {

    Flux<FlightOfferDto> findAll();

    Mono<FlightOfferDto> findById(Integer offerId);

    Flux<FlightOfferDto> findByFlightId(Integer flightId);

    Flux<FlightOfferDto> findAllDeals();

    Mono<FlightOfferDto> create(FlightOfferDto flightOfferDto);

    Mono<FlightOfferDto> update(Integer offerId, FlightOfferDto flightOfferDto);

    Mono<Void> delete(Integer offerId);
}