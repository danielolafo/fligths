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

import com.danielolafo.recommendation.Flights.application.dto.AirportDto;
import com.danielolafo.recommendation.Flights.application.service.AirportService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public Flux<AirportDto> getAll(@RequestParam(required = false) String city,
                                   @RequestParam(required = false) String country) {
        if (city != null) {
            return airportService.findByCity(city);
        }
        if (country != null) {
            return airportService.findByCountry(country);
        }
        return airportService.findAll();
    }

    @GetMapping("/{airportCode}")
    public Mono<AirportDto> getById(@PathVariable String airportCode) {
        return airportService.findById(airportCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AirportDto> create(@RequestBody AirportDto airportDto) {
        return airportService.create(airportDto);
    }

    @PutMapping("/{airportCode}")
    public Mono<AirportDto> update(@PathVariable String airportCode,
                                   @RequestBody AirportDto airportDto) {
        return airportService.update(airportCode, airportDto);
    }

    @DeleteMapping("/{airportCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String airportCode) {
        return airportService.delete(airportCode);
    }
}