package com.danielolafo.recommendation.Flights.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightOfferDto(
        Integer offerId,
        Integer flightId,
        String providerName,
        String cabinClass,
        BigDecimal price,
        String currency,
        Integer seatsRemaining,
        Boolean isDeal,
        BigDecimal discountPercentage,
        LocalDateTime validUntil,
        String bookingUrl) {
}