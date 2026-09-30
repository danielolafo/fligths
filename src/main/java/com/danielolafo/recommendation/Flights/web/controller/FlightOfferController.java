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

import com.danielolafo.recommendation.Flights.application.dto.FlightOfferDto;
import com.danielolafo.recommendation.Flights.application.service.FlightOfferService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/flight-offers")
public class FlightOfferController {

    private final FlightOfferService flightOfferService;

    public FlightOfferController(FlightOfferService flightOfferService) {
        this.flightOfferService = flightOfferService;
    }

    @GetMapping
    public Flux<FlightOfferDto> getAll(@RequestParam(required = false) Integer flightId,
                                       @RequestParam(defaultValue = "false") boolean dealsOnly) {
        if (dealsOnly) {
            return flightOfferService.findAllDeals();
        }
        if (flightId != null) {
            return flightOfferService.findByFlightId(flightId);
        }
        return flightOfferService.findAll();
    }

    @GetMapping("/{offerId}")
    public Mono<FlightOfferDto> getById(@PathVariable Integer offerId) {
        return flightOfferService.findById(offerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlightOfferDto> create(@RequestBody FlightOfferDto flightOfferDto) {
        return flightOfferService.create(flightOfferDto);
    }

    @PutMapping("/{offerId}")
    public Mono<FlightOfferDto> update(@PathVariable Integer offerId,
                                       @RequestBody FlightOfferDto flightOfferDto) {
        return flightOfferService.update(offerId, flightOfferDto);
    }

    @DeleteMapping("/{offerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Integer offerId) {
        return flightOfferService.delete(offerId);
    }
}