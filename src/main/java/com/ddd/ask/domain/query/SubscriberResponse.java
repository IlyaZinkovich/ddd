package com.ddd.ask.domain.query;

public class SubscriberResponse {

    private final String response;

    public SubscriberResponse(String response) {
        this.response = response;
    }

    public String response() {
        return response;
    }
}
