package com.danielolafo.recommendation.Flights.application.dto;

public record AirportDto(
        String airportCode,
        String airportName,
        String city,
        String country,
        String timezone) {
}