package com.ddd.ask.domain.query;

import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;

public class Query {

    private final QueryId id;
    private final SubscriberId subscriberId;
    private Question question;
    private String title;

    public Query(QueryId id, SubscriberId subscriberId, Question question) {
        this.id = id;
        this.question = question;
        this.subscriberId = subscriberId;
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
}
