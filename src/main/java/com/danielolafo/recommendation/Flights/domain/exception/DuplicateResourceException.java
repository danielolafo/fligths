package com.danielolafo.recommendation.Flights.domain.exception;

public class DuplicateResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateResourceException() {
        super();
    }

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String resource, Object identifier) {
        super(String.format("%s with identifier %s already exists", resource, identifier));
    }
}