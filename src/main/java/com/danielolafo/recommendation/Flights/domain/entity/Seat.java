package com.danielolafo.recommendation.Flights.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Table(name="seats")
public class Seat {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private Integer planeId;
	
	@Column
	private Integer seatNumber;

}
