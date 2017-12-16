package com.ddd.ask.application.service.response.finders;

import com.ddd.ask.domain.query.QueryId;

public class QueryResponsesFinder {

    private final QueryId queryId;

    public QueryResponsesFinder(QueryId queryId) {
        this.queryId = queryId;
    }

    public QueryId queryId() {
        return queryId;
    }
}
