package com.danielolafo.recommendation.Flights.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielolafo.recommendation.Flights.application.dto.AirlineDto;
import com.danielolafo.recommendation.Flights.application.service.AirlineService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public Flux<AirlineDto> getAll() {
        return airlineService.findAll();
    }

    @GetMapping("/{airlineCode}")
    public Mono<AirlineDto> getById(@PathVariable String airlineCode) {
        return airlineService.findById(airlineCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AirlineDto> create(@RequestBody AirlineDto airlineDto) {
        return airlineService.create(airlineDto);
    }

    @PutMapping("/{airlineCode}")
    public Mono<AirlineDto> update(@PathVariable String airlineCode,
                                   @RequestBody AirlineDto airlineDto) {
        return airlineService.update(airlineCode, airlineDto);
    }

    @DeleteMapping("/{airlineCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String airlineCode) {
        return airlineService.delete(airlineCode);
    }
}