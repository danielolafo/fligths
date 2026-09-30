package com.danielolafo.recommendation.Flights.application.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielolafo.recommendation.Flights.application.dto.FlightOfferDto;
import com.danielolafo.recommendation.Flights.application.service.FlightOfferService;
import com.danielolafo.recommendation.Flights.domain.entity.FlightOffer;
import com.danielolafo.recommendation.Flights.domain.exception.ResourceNotFoundException;
import com.danielolafo.recommendation.Flights.infrastructure.repository.FlightOfferRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class FlightOfferServiceImpl implements FlightOfferService {

    private final FlightOfferRepository flightOfferRepository;

    public FlightOfferServiceImpl(FlightOfferRepository flightOfferRepository) {
        this.flightOfferRepository = flightOfferRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightOfferDto> findAll() {
        return flightOfferRepository.findAll().map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<FlightOfferDto> findById(Integer offerId) {
        return findEntity(offerId).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightOfferDto> findByFlightId(Integer flightId) {
        return flightOfferRepository.findByFlightId(flightId).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<FlightOfferDto> findAllDeals() {
        return flightOfferRepository.findByIsDealTrue().map(this::toDto);
    }

    @Override
    public Mono<FlightOfferDto> create(FlightOfferDto flightOfferDto) {
        FlightOffer offer = new FlightOffer(null, flightOfferDto.flightId(), flightOfferDto.providerName(),
                flightOfferDto.cabinClass(), flightOfferDto.price(), flightOfferDto.currency(),
                flightOfferDto.seatsRemaining(), flightOfferDto.isDeal(), flightOfferDto.discountPercentage(),
                flightOfferDto.validUntil(), flightOfferDto.bookingUrl());
        return flightOfferRepository.save(offer).map(this::toDto);
    }

    @Override
    public Mono<FlightOfferDto> update(Integer offerId, FlightOfferDto flightOfferDto) {
        return findEntity(offerId)
                .flatMap(offer -> {
                    offer.setFlightId(flightOfferDto.flightId());
                    offer.setProviderName(flightOfferDto.providerName());
                    offer.setCabinClass(flightOfferDto.cabinClass());
                    offer.setPrice(flightOfferDto.price());
                    offer.setCurrency(flightOfferDto.currency());
                    offer.setSeatsRemaining(flightOfferDto.seatsRemaining());
                    offer.setIsDeal(flightOfferDto.isDeal());
                    offer.setDiscountPercentage(flightOfferDto.discountPercentage());
                    offer.setValidUntil(flightOfferDto.validUntil());
                    offer.setBookingUrl(flightOfferDto.bookingUrl());
                    return flightOfferRepository.save(offer);
                })
                .map(this::toDto);
    }

    @Override
    public Mono<Void> delete(Integer offerId) {
        return findEntity(offerId).flatMap(flightOfferRepository::delete);
    }

    private Mono<FlightOffer> findEntity(Integer offerId) {
        return flightOfferRepository.findById(offerId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("FlightOffer", offerId)));
    }

    private FlightOfferDto toDto(FlightOffer offer) {
        return new FlightOfferDto(offer.getOfferId(), offer.getFlightId(), offer.getProviderName(),
                offer.getCabinClass(), offer.getPrice(), offer.getCurrency(), offer.getSeatsRemaining(),
                offer.getIsDeal(), offer.getDiscountPercentage(), offer.getValidUntil(), offer.getBookingUrl());
    }
}