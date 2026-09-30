package com.danielolafo.recommendation.Flights.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielolafo.recommendation.Flights.application.dto.FlightScheduleDto;
import com.danielolafo.recommendation.Flights.application.service.FlightScheduleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/flight-schedules")
public class FlightScheduleController {

    private final FlightScheduleService flightScheduleService;

    public FlightScheduleController(FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    @GetMapping
    public Flux<FlightScheduleDto> getAll(@RequestParam(required = false) String departure,
                                          @RequestParam(required = false) String arrival) {
        if (departure != null && arrival != null) {
            return flightScheduleService.findByRoute(departure, arrival);
        }
        if (departure != null) {
            return flightScheduleService.findByDepartureAirport(departure);
        }
        if (arrival != null) {
            return flightScheduleService.findByArrivalAirport(arrival);
        }
        return flightScheduleService.findAll();
    }

    @GetMapping("/{scheduleId}")
    public Mono<FlightScheduleDto> getById(@PathVariable Integer scheduleId) {
        return flightScheduleService.findById(scheduleId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlightScheduleDto> create(@RequestBody FlightScheduleDto flightScheduleDto) {
        return flightScheduleService.create(flightScheduleDto);
    }

    @PutMapping("/{scheduleId}")
    public Mono<FlightScheduleDto> update(@PathVariable Integer scheduleId,
                                          @RequestBody FlightScheduleDto flightScheduleDto) {
        return flightScheduleService.update(scheduleId, flightScheduleDto);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Integer scheduleId) {
        return flightScheduleService.delete(scheduleId);
    }
}