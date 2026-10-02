package com.danielolafo.recommendation.Flights.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Table(name="flight_seats")
public class FlightSeat {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private Integer bookingId;
	
	@Column
	private Integer flightId;

}
