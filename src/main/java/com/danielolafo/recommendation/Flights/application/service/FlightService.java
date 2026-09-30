package com.danielolafo.recommendation.Flights.application.service;

import java.time.LocalDate;

import com.danielolafo.recommendation.Flights.application.dto.FlightDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {

    Flux<FlightDto> findAll();

    Mono<FlightDto> findById(Integer flightId);

    Flux<FlightDto> findByStatus(String status);

    Flux<FlightDto> findByDepartureDate(LocalDate departureDate);

    Mono<FlightDto> create(FlightDto flightDto);

    Mono<FlightDto> update(Integer flightId, FlightDto flightDto);

    Mono<Void> delete(Integer flightId);
}