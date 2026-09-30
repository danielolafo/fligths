package com.danielolafo.recommendation.Flights.infrastructure.repository;

import java.time.LocalDate;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.danielolafo.recommendation.Flights.domain.entity.Flight;

import reactor.core.publisher.Flux;

@Repository
public interface FlightRepository extends ReactiveCrudRepository<Flight, Integer> {

    Flux<Flight> findByStatus(String status);

    Flux<Flight> findByDepartureDate(LocalDate departureDate);

    Flux<Flight> findByScheduleId(Integer scheduleId);
}