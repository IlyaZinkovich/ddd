package com.ddd.ask.domain.query;

import com.ddd.ask.domain.editor.EditorId;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;

import java.util.Optional;

public class Query {

    private final QueryId id;
    private final SubscriberId subscriberId;
    private Question question;
    private String title;
    private Optional<EditorId> assigneeId;
    private QueryStatus status;

    public Query(QueryId id, SubscriberId subscriberId, Question question) {
        this.id = id;
        this.question = question;
        this.subscriberId = subscriberId;
        this.assigneeId = Optional.empty();
        this.status = QueryStatus.NOT_STARTED;
    }

    public QueryId id() {
        return id;
    }

    public SubscriberId subscriberId() {
        return subscriberId;
    }

    public Question question() {
        return question;
    }

    public String title() {
        return title;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void assign(EditorId editorId) {
        this.assigneeId = Optional.of(editorId);
    }

    public Optional<EditorId> assigneeId() {
        return assigneeId;
    }

    public void changeStatus(QueryStatus status) {
        this.status = status;
    }

    public QueryStatus status() {
        return status;
    }

    public void resetStatusAndUnassign() {
        this.status = QueryStatus.NOT_STARTED;
        this.assigneeId = Optional.empty();
    }
}
