package com.danielolafo.recommendation.Flights.domain.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("flights_prices")
public class FlightPrice implements Persistable<Integer> {

    @Id
    @Column("id")
    private Integer id;

    @Transient
    private boolean isNew = false;

    @Column("origin")
    private String origin;

    @Column("destination")
    private String destination;

    @Column("month_num")
    private String monthNum;

    @Column("day_of_month")
    private Integer dayOfMonth;

    @Column("price")
    private BigDecimal price;

    @Column("airline_code")
    private String airlineCode;

    public FlightPrice() {
    }

    public FlightPrice(Integer id, String origin, String destination, String monthNum, Integer dayOfMonth,
                       BigDecimal price, String airlineCode) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.monthNum = monthNum;
        this.dayOfMonth = dayOfMonth;
        this.price = price;
        this.airlineCode = airlineCode;
        this.isNew = true;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
}