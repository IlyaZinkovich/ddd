package com.ddd.ask.application.service.query.finders;

import com.ddd.ask.domain.query.QueryId;

public class QueryByIdFinder {

    private final QueryId queryId;

    public QueryByIdFinder(QueryId queryId) {
        this.queryId = queryId;
    }

    public QueryId queryId() {
        return queryId;
    }
}
