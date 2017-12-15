package com.ddd.ask.application.service.response;

import com.ddd.ask.domain.events.DomainEvent;
import com.ddd.ask.domain.query.QueryId;

import java.time.Instant;

public class SubscriberResponseAddedEvent implements DomainEvent {

    private final QueryId queryId;
    private final Instant occurredOn;
    private final int version;

    public SubscriberResponseAddedEvent(QueryId queryId) {
        this.queryId = queryId;
        this.version = 1;
        this.occurredOn = Instant.now();
    }

    @Override
    public int eventVersion() {
        return version;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public QueryId queryId() {
        return queryId;
    }


}
