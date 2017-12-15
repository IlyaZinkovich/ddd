package com.ddd.ask.application.service.query.commands;

import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.SubscriberResponse;

public class AddSubscriberResponseCommand {

    private final QueryId queryId;
    private final SubscriberResponse response;

    public AddSubscriberResponseCommand(QueryId queryId, SubscriberResponse response) {
        this.queryId = queryId;
        this.response = response;
    }

    public QueryId queryId() {
        return queryId;
    }

    public SubscriberResponse response() {
        return response;
    }
}
