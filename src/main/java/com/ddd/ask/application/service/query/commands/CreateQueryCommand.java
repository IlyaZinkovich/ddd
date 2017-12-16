package com.ddd.ask.application.service.query.commands;

import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;

public class CreateQueryCommand {

    private final SubscriberId subscriberId;

    private final Question question;

    public CreateQueryCommand(SubscriberId subscriberId, Question question) {
        this.subscriberId = subscriberId;
        this.question = question;
    }

    public SubscriberId subscriberId() {
        return subscriberId;
    }

    public Question question() {
        return question;
    }
}
