package com.danielolafo.recommendation.Flights.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielolafo.recommendation.Flights.application.dto.AirlineDto;
import com.danielolafo.recommendation.Flights.application.service.AirlineService;
import com.danielolafo.recommendation.Flights.domain.entity.Airline;
import com.danielolafo.recommendation.Flights.domain.exception.DuplicateResourceException;
import com.danielolafo.recommendation.Flights.domain.exception.ResourceNotFoundException;
import com.danielolafo.recommendation.Flights.infrastructure.repository.AirlineRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<AirlineDto> findAll() {
        return airlineRepository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<AirlineDto> findById(String airlineCode) {
        return findEntity(airlineCode).map(this::toDto);
    }

    @Override
    public Mono<AirlineDto> create(AirlineDto airlineDto) {
        return airlineRepository.existsByAirlineCode(airlineDto.airlineCode())
                .flatMap(exists -> exists
                        ? Mono.error(new DuplicateResourceException("Airline", airlineDto.airlineCode()))
                        : airlineRepository.save(toEntity(airlineDto)))
                .map(this::toDto);
    }

    @Override
    public Mono<AirlineDto> update(String airlineCode, AirlineDto airlineDto) {
        return findEntity(airlineCode)
                .flatMap(airline -> {
                    airline.setAirlineName(airlineDto.airlineName());
                    airline.setCountryOfOrigin(airlineDto.countryOfOrigin());
                    return airlineRepository.save(airline);
                })
                .map(this::toDto);
    }

    @Override
    public Mono<Void> delete(String airlineCode) {
        return findEntity(airlineCode).flatMap(airlineRepository::delete);
    }

    private Mono<Airline> findEntity(String airlineCode) {
        return airlineRepository.findById(airlineCode)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Airline", airlineCode)));
    }

    private Airline toEntity(AirlineDto airlineDto) {
        return new Airline(airlineDto.airlineCode(), airlineDto.airlineName(), airlineDto.countryOfOrigin());
    }

    private AirlineDto toDto(Airline airline) {
        return new AirlineDto(airline.getAirlineCode(), airline.getAirlineName(), airline.getCountryOfOrigin());
    }
}