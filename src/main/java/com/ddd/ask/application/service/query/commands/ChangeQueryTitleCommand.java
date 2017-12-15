package com.ddd.ask.application.service.query.commands;

import com.ddd.ask.domain.query.QueryId;

public class ChangeQueryTitleCommand {

    private QueryId queryId;
    private String title;

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
