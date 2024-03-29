package com.ddd.ask.application.service.query.commands;

import com.ddd.ask.domain.query.QueryId;

public class ChangeQueryTitleCommand {

    private final QueryId queryId;
    private final String title;

    public ChangeQueryTitleCommand(QueryId queryId, String title) {
        this.queryId = queryId;
        this.title = title;
    }

    public QueryId queryId() {
        return queryId;
    }

    public String title() {
        return title;
    }
}
