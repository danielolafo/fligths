package com.danielolafo.recommendation.Flights.application.dto;

import java.math.BigDecimal;

public record FlightPriceDto(
        Integer id,
        String origin,
        String destination,
        String monthNum,
        Integer dayOfMonth,
        BigDecimal price,
        String airlineCode) {
}