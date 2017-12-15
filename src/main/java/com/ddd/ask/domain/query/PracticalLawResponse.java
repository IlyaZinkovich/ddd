package com.ddd.ask.domain.query;

import java.time.Instant;

public class PracticalLawResponse implements Response {

    private final String text;
    private final Instant modificationDate;

    public PracticalLawResponse(String responseText) {
        this.text = responseText;
        this.modificationDate = Instant.now();
    }

    @Override
    public Instant modificationDate() {
        return modificationDate;
    }

    @Override
    public String text() {
        return text;
    }
}
