package com.danielolafo.recommendation.Flights.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielolafo.recommendation.Flights.application.dto.FlightScheduleDto;
import com.danielolafo.recommendation.Flights.application.service.FlightScheduleService;
import com.danielolafo.recommendation.Flights.domain.entity.FlightSchedule;
import com.danielolafo.recommendation.Flights.domain.exception.ResourceNotFoundException;
import com.danielolafo.recommendation.Flights.infrastructure.repository.FlightScheduleRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;

    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository) {
        this.flightScheduleRepository = flightScheduleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightScheduleDto> findAll() {
        return flightScheduleRepository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FlightScheduleDto> findById(Integer scheduleId) {
        return findEntity(scheduleId).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightScheduleDto> findByDepartureAirport(String departureAirport) {
        return flightScheduleRepository.findByDepartureAirport(departureAirport).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightScheduleDto> findByArrivalAirport(String arrivalAirport) {
        return flightScheduleRepository.findByArrivalAirport(arrivalAirport).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightScheduleDto> findByRoute(String departureAirport, String arrivalAirport) {
        return flightScheduleRepository
                .findByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport)
                .map(this::toDto);
    }

    @Override
    public Mono<FlightScheduleDto> create(FlightScheduleDto flightScheduleDto) {
        FlightSchedule schedule = new FlightSchedule(null, flightScheduleDto.airlineCode(),
                flightScheduleDto.flightNumber(), flightScheduleDto.departureAirport(),
                flightScheduleDto.arrivalAirport(), flightScheduleDto.scheduledDepartureTime(),
                flightScheduleDto.scheduledArrivalTime(), flightScheduleDto.daysOfWeek());
        return flightScheduleRepository.save(schedule).map(this::toDto);
    }

    @Override
    public Mono<FlightScheduleDto> update(Integer scheduleId, FlightScheduleDto flightScheduleDto) {
        return findEntity(scheduleId)
                .flatMap(schedule -> {
                    schedule.setAirlineCode(flightScheduleDto.airlineCode());
                    schedule.setFlightNumber(flightScheduleDto.flightNumber());
                    schedule.setDepartureAirport(flightScheduleDto.departureAirport());
                    schedule.setArrivalAirport(flightScheduleDto.arrivalAirport());
                    schedule.setScheduledDepartureTime(flightScheduleDto.scheduledDepartureTime());
                    schedule.setScheduledArrivalTime(flightScheduleDto.scheduledArrivalTime());
                    schedule.setDaysOfWeek(flightScheduleDto.daysOfWeek());
                    return flightScheduleRepository.save(schedule);
                })
                .map(this::toDto);
    }

    @Override
    public Mono<Void> delete(Integer scheduleId) {
        return findEntity(scheduleId).flatMap(flightScheduleRepository::delete);
    }

    private Mono<FlightSchedule> findEntity(Integer scheduleId) {
        return flightScheduleRepository.findById(scheduleId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("FlightSchedule", scheduleId)));
    }

    private FlightScheduleDto toDto(FlightSchedule schedule) {
        return new FlightScheduleDto(schedule.getScheduleId(), schedule.getAirlineCode(),
                schedule.getFlightNumber(), schedule.getDepartureAirport(), schedule.getArrivalAirport(),
                schedule.getScheduledDepartureTime(), schedule.getScheduledArrivalTime(),
                schedule.getDaysOfWeek());
    }
}