package com.danielolafo.recommendation.Flights.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table("planes")
public class Plane {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private Integer modelId;
	
	private Integer numberOfSeats;
	
	private String status;
	
	private String airlineCode;

}
