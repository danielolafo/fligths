package com.danielolafo.recommendation.Flights.application.dto;

public record AirlineDto(
        String airlineCode,
        String airlineName,
        String countryOfOrigin) {
}