package com.danielolafo.recommendation.Flights.web.controller;

import java.math.BigDecimal;

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

import com.danielolafo.recommendation.Flights.application.dto.FlightPriceDto;
import com.danielolafo.recommendation.Flights.application.service.FlightPriceService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/flight-prices")
public class FlightPriceController {

    private final FlightPriceService flightPriceService;

    public FlightPriceController(FlightPriceService flightPriceService) {
        this.flightPriceService = flightPriceService;
    }

    @GetMapping
    public Flux<FlightPriceDto> getAll(@RequestParam(required = false) String origin,
                                       @RequestParam(required = false) String destination,
                                       @RequestParam(required = false) String month) {
        if (origin != null && destination != null && month != null) {
            return flightPriceService.findByRouteAndMonth(origin, destination, month);
        }
        if (origin != null && destination != null) {
            return flightPriceService.findByRoute(origin, destination);
        }
        return flightPriceService.findAll();
    }

    /**
     * 
     * @param origin
     * @param destination
     * @return
     * @author Daniel Orlando López Ochoa
     */
    @GetMapping("/best-price")
    public Mono<BigDecimal> getBestPrice(@RequestParam String origin,
                                         @RequestParam String destination) {
        return flightPriceService.findBestPrice(origin, destination);
    }

    @GetMapping("/{id}")
    public Mono<FlightPriceDto> getById(@PathVariable Integer id) {
        return flightPriceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FlightPriceDto> create(@RequestBody FlightPriceDto flightPriceDto) {
        return flightPriceService.create(flightPriceDto);
    }

    @PutMapping("/{id}")
    public Mono<FlightPriceDto> update(@PathVariable Integer id,
                                       @RequestBody FlightPriceDto flightPriceDto) {
        return flightPriceService.update(id, flightPriceDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Integer id) {
        return flightPriceService.delete(id);
    }
}