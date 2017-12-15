package com.ddd.ask.application.service;

import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryStatus;

public class ChangeQueryStatusCommand {

    private final QueryId queryId;
    private final QueryStatus status;

    public ChangeQueryStatusCommand(QueryId queryId, QueryStatus status) {
        this.queryId = queryId;
        this.status = status;
    }

    public QueryId queryId() {
        return queryId;
    }

    public QueryStatus status() {
        return status;
    }
}
