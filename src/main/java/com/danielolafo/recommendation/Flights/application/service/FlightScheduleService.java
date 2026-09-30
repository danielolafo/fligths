package com.danielolafo.recommendation.Flights.application.service;

import com.danielolafo.recommendation.Flights.application.dto.FlightScheduleDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightScheduleService {

    Flux<FlightScheduleDto> findAll();

    Mono<FlightScheduleDto> findById(Integer scheduleId);

    Flux<FlightScheduleDto> findByDepartureAirport(String departureAirport);

    Flux<FlightScheduleDto> findByArrivalAirport(String arrivalAirport);

    Flux<FlightScheduleDto> findByRoute(String departureAirport, String arrivalAirport);

    Mono<FlightScheduleDto> create(FlightScheduleDto flightScheduleDto);

    Mono<FlightScheduleDto> update(Integer scheduleId, FlightScheduleDto flightScheduleDto);

    Mono<Void> delete(Integer scheduleId);
}