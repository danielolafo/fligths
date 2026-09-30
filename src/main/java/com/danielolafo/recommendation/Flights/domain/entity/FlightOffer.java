package com.danielolafo.recommendation.Flights.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("flight_offers")
public class FlightOffer implements Persistable<Integer> {

    @Id
    @Column("offer_id")
    private Integer offerId;

    @Transient
    private boolean isNew = false;

    @Column("flight_id")
    private Integer flightId;

    @Column("provider_name")
    private String providerName;

    @Column("cabin_class")
    private String cabinClass;

    @Column("price")
    private BigDecimal price;

    @Column("currency")
    private String currency;

    @Column("seats_remaining")
    private Integer seatsRemaining;

    @Column("is_deal")
    private Boolean isDeal;

    @Column("discount_percentage")
    private BigDecimal discountPercentage;

    @Column("valid_until")
    private LocalDateTime validUntil;

    @Column("booking_url")
    private String bookingUrl;

    public FlightOffer() {
    }

    public FlightOffer(Integer offerId, Integer flightId, String providerName, String cabinClass, BigDecimal price,
                       String currency, Integer seatsRemaining, Boolean isDeal, BigDecimal discountPercentage,
                       LocalDateTime validUntil, String bookingUrl) {
        this.offerId = offerId;
        this.flightId = flightId;
        this.providerName = providerName;
        this.cabinClass = cabinClass;
        this.price = price;
        this.currency = currency;
        this.seatsRemaining = seatsRemaining;
        this.isDeal = isDeal;
        this.discountPercentage = discountPercentage;
        this.validUntil = validUntil;
        this.bookingUrl = bookingUrl;
        this.isNew = true;
    }

    @Override
    public Integer getId() {
        return offerId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(Integer seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    public Boolean getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(Boolean isDeal) {
        this.isDeal = isDeal;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }
}