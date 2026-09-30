package com.danielolafo.recommendation.Flights.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("airports")
public class Airport implements Persistable<String> {

    @Id
    @Column("airport_code")
    private String airportCode;

    @Transient
    private boolean isNew = false;

    @Column("airport_name")
    private String airportName;

    @Column("city")
    private String city;

    @Column("country")
    private String country;

    @Column("timezone")
    private String timezone;

    public Airport() {
    }

    public Airport(String airportCode, String airportName, String city, String country, String timezone) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
        this.timezone = timezone;
        this.isNew = true;
    }

    @Override
    public String getId() {
        return airportCode;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}