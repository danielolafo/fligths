package com.danielolafo.recommendation.Flights.application.service;

import com.danielolafo.recommendation.Flights.application.dto.AirlineDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirlineService {

    Flux<AirlineDto> findAll();

    Mono<AirlineDto> findById(String airlineCode);

    Mono<AirlineDto> create(AirlineDto airlineDto);

    Mono<AirlineDto> update(String airlineCode, AirlineDto airlineDto);

    Mono<Void> delete(String airlineCode);
}