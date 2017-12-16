package com.ddd.ask.presentation.query;

import com.ddd.ask.domain.editor.EditorId;
import com.ddd.ask.domain.query.Query;

import java.util.Optional;

public class QueryResource {

    private String title;
    private String question;
    private String subscriberId;
    private Optional<String> assigneeId;
    private String status;

    public QueryResource(Query query) {
        this.title = query.title();
        this.question = query.question().text();
        this.subscriberId = query.subscriberId().id();
        this.assigneeId = query.assigneeId().map(EditorId::username);
        this.status = query.status().name();
    }

    public String title() {
        return title;
    }

    public String question() {
        return question;
    }

    public String subscriberId() {
        return subscriberId;
    }

    public Optional<String> assigneeId() {
        return assigneeId;
    }

    public String status() {
        return status;
    }
}
