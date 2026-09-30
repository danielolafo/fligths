package com.danielolafo.recommendation.Flights.application.service;

import java.math.BigDecimal;

import com.danielolafo.recommendation.Flights.application.dto.FlightPriceDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightPriceService {

    Flux<FlightPriceDto> findAll();

    Mono<FlightPriceDto> findById(Integer id);

    Flux<FlightPriceDto> findByRoute(String origin, String destination);

    Flux<FlightPriceDto> findByRouteAndMonth(String origin, String destination, String monthNum);

    Mono<BigDecimal> findBestPrice(String origin, String destination);

    Mono<FlightPriceDto> create(FlightPriceDto flightPriceDto);

    Mono<FlightPriceDto> update(Integer id, FlightPriceDto flightPriceDto);

    Mono<Void> delete(Integer id);
}