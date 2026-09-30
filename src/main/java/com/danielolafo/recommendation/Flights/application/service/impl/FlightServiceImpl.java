package com.danielolafo.recommendation.Flights.application.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielolafo.recommendation.Flights.application.dto.FlightDto;
import com.danielolafo.recommendation.Flights.application.service.FlightService;
import com.danielolafo.recommendation.Flights.domain.entity.Flight;
import com.danielolafo.recommendation.Flights.domain.exception.ResourceNotFoundException;
import com.danielolafo.recommendation.Flights.infrastructure.repository.FlightRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightDto> findAll() {
        return flightRepository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FlightDto> findById(Integer flightId) {
        return findEntity(flightId).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightDto> findByStatus(String status) {
        return flightRepository.findByStatus(status).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightDto> findByDepartureDate(LocalDate departureDate) {
        return flightRepository.findByDepartureDate(departureDate).map(this::toDto);
    }

    @Override
    public Mono<FlightDto> create(FlightDto flightDto) {
        Flight flight = new Flight(null, flightDto.scheduleId(), flightDto.departureDate(),
                flightDto.arrivalDate(), flightDto.actualDepartureTime(), flightDto.actualArrivalTime(),
                flightDto.status());
        return flightRepository.save(flight).map(this::toDto);
    }

    @Override
    public Mono<FlightDto> update(Integer flightId, FlightDto flightDto) {
        return findEntity(flightId)
                .flatMap(flight -> {
                    flight.setScheduleId(flightDto.scheduleId());
                    flight.setDepartureDate(flightDto.departureDate());
                    flight.setArrivalDate(flightDto.arrivalDate());
                    flight.setActualDepartureTime(flightDto.actualDepartureTime());
                    flight.setActualArrivalTime(flightDto.actualArrivalTime());
                    flight.setStatus(flightDto.status());
                    return flightRepository.save(flight);
                })
                .map(this::toDto);
    }

    @Override
    public Mono<Void> delete(Integer flightId) {
        return findEntity(flightId).flatMap(flightRepository::delete);
    }

    private Mono<Flight> findEntity(Integer flightId) {
        return flightRepository.findById(flightId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Flight", flightId)));
    }

    private FlightDto toDto(Flight flight) {
        return new FlightDto(flight.getFlightId(), flight.getScheduleId(), flight.getDepartureDate(),
                flight.getArrivalDate(), flight.getActualDepartureTime(), flight.getActualArrivalTime(),
                flight.getStatus());
    }
}