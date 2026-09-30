package com.danielolafo.recommendation.Flights.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("airlines")
public class Airline implements Persistable<String> {

    @Id
    @Column("airline_code")
    private String airlineCode;

    @Transient
    private boolean isNew = false;

    @Column("airline_name")
    private String airlineName;

    @Column("country_of_origin")
    private String countryOfOrigin;

    public Airline() {
    }

    public Airline(String airlineCode, String airlineName, String countryOfOrigin) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.countryOfOrigin = countryOfOrigin;
        this.isNew = true;
    }

    @Override
    public String getId() {
        return airlineCode;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
}