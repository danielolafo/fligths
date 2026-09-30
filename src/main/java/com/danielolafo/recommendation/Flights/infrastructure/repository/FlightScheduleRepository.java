package com.danielolafo.recommendation.Flights.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.danielolafo.recommendation.Flights.domain.entity.FlightSchedule;

import reactor.core.publisher.Flux;

@Repository
public interface FlightScheduleRepository extends ReactiveCrudRepository<FlightSchedule, Integer> {

    Flux<FlightSchedule> findByDepartureAirport(String departureAirport);

    Flux<FlightSchedule> findByArrivalAirport(String arrivalAirport);

    Flux<FlightSchedule> findByAirlineCode(String airlineCode);

    Flux<FlightSchedule> findByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
}