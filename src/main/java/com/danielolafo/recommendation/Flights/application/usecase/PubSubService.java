package com.danielolafo.recommendation.Flights.application.usecase;

public interface PubSubService {
	
	public void publishMessage(String topicName, String message);

}
