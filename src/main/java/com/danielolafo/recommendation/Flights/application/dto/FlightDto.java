package com.danielolafo.recommendation.Flights.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FlightDto(
        Integer flightId,
        Integer scheduleId,
        LocalDate departureDate,
        LocalDate arrivalDate,
        LocalDateTime actualDepartureTime,
        LocalDateTime actualArrivalTime,
        String status) {
}