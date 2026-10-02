package com.danielolafo.recommendation.Flights.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="booking_seats")
public class BookingSeat {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private Integer bookingId;

}
