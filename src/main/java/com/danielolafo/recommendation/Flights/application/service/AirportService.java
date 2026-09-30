package com.danielolafo.recommendation.Flights.application.service;

import com.danielolafo.recommendation.Flights.application.dto.AirportDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirportService {

    Flux<AirportDto> findAll();

    Mono<AirportDto> findById(String airportCode);

    Flux<AirportDto> findByCountry(String country);

    Flux<AirportDto> findByCity(String city);

    Mono<AirportDto> create(AirportDto airportDto);

    Mono<AirportDto> update(String airportCode, AirportDto airportDto);

    Mono<Void> delete(String airportCode);
}