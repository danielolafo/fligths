package com.danielolafo.recommendation.Flights.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielolafo.recommendation.Flights.application.dto.AirportDto;
import com.danielolafo.recommendation.Flights.application.service.AirportService;
import com.danielolafo.recommendation.Flights.domain.entity.Airport;
import com.danielolafo.recommendation.Flights.domain.exception.DuplicateResourceException;
import com.danielolafo.recommendation.Flights.domain.exception.ResourceNotFoundException;
import com.danielolafo.recommendation.Flights.infrastructure.repository.AirportRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<AirportDto> findAll() {
        return airportRepository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<AirportDto> findById(String airportCode) {
        return findEntity(airportCode).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<AirportDto> findByCountry(String country) {
        return airportRepository.findByCountry(country).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<AirportDto> findByCity(String city) {
        return airportRepository.findByCity(city).map(this::toDto);
    }

    @Override
    public Mono<AirportDto> create(AirportDto airportDto) {
        return airportRepository.existsByAirportCode(airportDto.airportCode())
                .flatMap(exists -> exists
                        ? Mono.error(new DuplicateResourceException("Airport", airportDto.airportCode()))
                        : airportRepository.save(toEntity(airportDto)))
                .map(this::toDto);
    }

    @Override
    public Mono<AirportDto> update(String airportCode, AirportDto airportDto) {
        return findEntity(airportCode)
                .flatMap(airport -> {
                    airport.setAirportName(airportDto.airportName());
                    airport.setCity(airportDto.city());
                    airport.setCountry(airportDto.country());
                    airport.setTimezone(airportDto.timezone());
                    return airportRepository.save(airport);
                })
                .map(this::toDto);
    }

    @Override
    public Mono<Void> delete(String airportCode) {
        return findEntity(airportCode).flatMap(airportRepository::delete);
    }

    private Mono<Airport> findEntity(String airportCode) {
        return airportRepository.findById(airportCode)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Airport", airportCode)));
    }

    private Airport toEntity(AirportDto airportDto) {
        return new Airport(airportDto.airportCode(), airportDto.airportName(),
                airportDto.city(), airportDto.country(), airportDto.timezone());
    }

    private AirportDto toDto(Airport airport) {
        return new AirportDto(airport.getAirportCode(), airport.getAirportName(),
                airport.getCity(), airport.getCountry(), airport.getTimezone());
    }
}