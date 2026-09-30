package com.danielolafo.recommendation.Flights.domain.entity;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("flight_schedules")
public class FlightSchedule implements Persistable<Integer> {

    @Id
    @Column("schedule_id")
    private Integer scheduleId;

    @Transient
    private boolean isNew = false;

    @Column("airline_code")
    private String airlineCode;

    @Column("flight_number")
    private String flightNumber;

    @Column("departure_airport")
    private String departureAirport;

    @Column("arrival_airport")
    private String arrivalAirport;

    @Column("scheduled_departure_time")
    private LocalTime scheduledDepartureTime;

    @Column("scheduled_arrival_time")
    private LocalTime scheduledArrivalTime;

    @Column("days_of_week")
    private String daysOfWeek;

    public FlightSchedule() {
    }

    public FlightSchedule(Integer scheduleId, String airlineCode, String flightNumber, String departureAirport,
                          String arrivalAirport, LocalTime scheduledDepartureTime, LocalTime scheduledArrivalTime,
                          String daysOfWeek) {
        this.scheduleId = scheduleId;
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.daysOfWeek = daysOfWeek;
        this.isNew = true;
    }

    @Override
    public Integer getId() {
        return scheduleId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(LocalTime scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public LocalTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(LocalTime scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}