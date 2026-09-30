package com.danielolafo.recommendation.Flights.web.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.danielolafo.recommendation.Flights.application.dto.FlightDto;
import com.danielolafo.recommendation.Flights.application.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public Flux<FlightDto> getAll(@RequestParam(required = false) String status,
                                  @RequestParam(required = false)
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        if (status != null) {
            return flightService.findByStatus(status);
        }
        if (departureDate != null) {
            return flightService.findByDepartureDate(departureDate);
        }
        return flightService.findAll();
    }

    @GetMapping("/{flightId}")
    public Mono<FlightDto> getById(@PathVariable Integer flightId) {
        return flightService.findById(flightId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlightDto> create(@RequestBody FlightDto flightDto) {
        return flightService.create(flightDto);
    }

    @PutMapping("/{flightId}")
    public Mono<FlightDto> update(@PathVariable Integer flightId,
                                  @RequestBody FlightDto flightDto) {
        return flightService.update(flightId, flightDto);
    }

    @DeleteMapping("/{flightId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Integer flightId) {
        return flightService.delete(flightId);
    }
}