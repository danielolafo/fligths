package com.danielolafo.recommendation.Flights.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danielolafo.recommendation.Flights.application.usecase.PubSubService;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PubSubServiceImpl implements PubSubService {

	@Autowired
    private PubSubTemplate pubSubTemplate;

	@Override
    public void publishMessage(String topicName, String message) {
        pubSubTemplate.publish(topicName, message);
        log.info("Message published successfully to {}", topicName);
    }

}
