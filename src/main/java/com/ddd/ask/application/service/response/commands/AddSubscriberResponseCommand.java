package com.ddd.ask.application.service.response.commands;

import com.ddd.ask.domain.query.QueryId;

public class AddSubscriberResponseCommand {

    private final QueryId queryId;
    private final String responseText;

    public AddSubscriberResponseCommand(QueryId queryId, String responseText) {
        this.queryId = queryId;
        this.responseText = responseText;
    }

    public QueryId queryId() {
        return queryId;
    }

    public String responseText() {
        return responseText;
    }
}
