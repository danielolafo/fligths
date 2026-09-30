package com.danielolafo.recommendation.Flights.application.dto;

import java.time.LocalTime;

public record FlightScheduleDto(
        Integer scheduleId,
        String airlineCode,
        String flightNumber,
        String departureAirport,
        String arrivalAirport,
        LocalTime scheduledDepartureTime,
        LocalTime scheduledArrivalTime,
        String daysOfWeek) {
}