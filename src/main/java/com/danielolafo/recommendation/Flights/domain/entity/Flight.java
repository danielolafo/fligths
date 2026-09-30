package com.danielolafo.recommendation.Flights.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("flights")
public class Flight implements Persistable<Integer> {

    @Id
    @Column("flight_id")
    private Integer flightId;

    @Transient
    private boolean isNew = false;

    @Column("schedule_id")
    private Integer scheduleId;

    @Column("departure_date")
    private LocalDate departureDate;

    @Column("arrival_date")
    private LocalDate arrivalDate;

    @Column("actual_departure_time")
    private LocalDateTime actualDepartureTime;

    @Column("actual_arrival_time")
    private LocalDateTime actualArrivalTime;

    @Column("status")
    private String status;

    public Flight() {
    }

    public Flight(Integer flightId, Integer scheduleId, LocalDate departureDate, LocalDate arrivalDate,
                  LocalDateTime actualDepartureTime, LocalDateTime actualArrivalTime, String status) {
        this.flightId = flightId;
        this.scheduleId = scheduleId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.actualDepartureTime = actualDepartureTime;
        this.actualArrivalTime = actualArrivalTime;
        this.status = status;
        this.isNew = true;
    }

    @Override
    public Integer getId() {
        return flightId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public void setActualDepartureTime(LocalDateTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}