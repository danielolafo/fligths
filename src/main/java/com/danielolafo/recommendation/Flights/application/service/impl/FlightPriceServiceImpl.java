package com.danielolafo.recommendation.Flights.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielolafo.recommendation.Flights.application.dto.FlightPriceDto;
import com.danielolafo.recommendation.Flights.application.service.FlightPriceService;
import com.danielolafo.recommendation.Flights.application.usecase.PubSubService;
import com.danielolafo.recommendation.Flights.domain.entity.FlightPrice;
import com.danielolafo.recommendation.Flights.domain.exception.ResourceNotFoundException;
import com.danielolafo.recommendation.Flights.infrastructure.repository.FlightPriceRepository;
import com.danielolafo.recommendation.Flights.utils.TopicConstants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FlightPriceServiceImpl implements FlightPriceService {

    private final FlightPriceRepository flightPriceRepository;
    private final PubSubService pubSubService;

    public FlightPriceServiceImpl(
    		FlightPriceRepository flightPriceRepository,
    		PubSubService pubSubService) {
        this.flightPriceRepository = flightPriceRepository;
        this.pubSubService = pubSubService;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightPriceDto> findAll() {
        return flightPriceRepository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FlightPriceDto> findById(Integer id) {
        return findEntity(id).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightPriceDto> findByRoute(String origin, String destination) {
        return flightPriceRepository
                .findByOriginAndDestinationOrderByDayOfMonthAsc(origin, destination)
                .map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightPriceDto> findByRouteAndMonth(String origin, String destination, String monthNum) {
        return flightPriceRepository
                .findByOriginAndDestinationAndMonthNum(origin, destination, monthNum)
                .map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FlightPriceDto> findBestPrice(String origin, String destination) {
        return flightPriceRepository.findByOriginAndDestination(origin, destination)
        		.sort((a,b)->a.getPrice().compareTo(b.getPrice()))
        		.elementAt(0)
        		.map(fp -> new FlightPriceDto(fp.getId(), fp.getOrigin(), fp.getDestination(), fp.getMonthNum(), fp.getDayOfMonth(), fp.getPrice(), fp.getAirlineCode()))
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("FlightPrice route", origin + " - " + destination)));
    }

    @Override
    public Mono<FlightPriceDto> create(FlightPriceDto flightPriceDto) {
        FlightPrice price = new FlightPrice(null, flightPriceDto.origin(), flightPriceDto.destination(),
                flightPriceDto.monthNum(), flightPriceDto.dayOfMonth(), flightPriceDto.price(),
                flightPriceDto.airlineCode());
        return flightPriceRepository.save(price).map(this::toDto);
    }

    @Override
    public Mono<FlightPriceDto> update(Integer id, FlightPriceDto flightPriceDto) {
        return findEntity(id)
                .flatMap(price -> {
                    price.setOrigin(flightPriceDto.origin());
                    price.setDestination(flightPriceDto.destination());
                    price.setMonthNum(flightPriceDto.monthNum());
                    price.setDayOfMonth(flightPriceDto.dayOfMonth());
                    price.setPrice(flightPriceDto.price());
                    price.setAirlineCode(flightPriceDto.airlineCode());
                    return flightPriceRepository.save(price);
                })
                .map(this::toDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return findEntity(id).flatMap(flightPriceRepository::delete)
        		.doOnSuccess(s -> pubSubService.publishMessage(TopicConstants.T_BOOKING, id.toString()))
        		.doOnError(s -> pubSubService.publishMessage(TopicConstants.T_BOOKING, id.toString()));
    }

    private Mono<FlightPrice> findEntity(Integer id) {
        return flightPriceRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("FlightPrice", id)));
    }

    private FlightPriceDto toDto(FlightPrice price) {
        return new FlightPriceDto(price.getId(), price.getOrigin(), price.getDestination(),
                price.getMonthNum(), price.getDayOfMonth(), price.getPrice(), price.getAirlineCode());
    }
}