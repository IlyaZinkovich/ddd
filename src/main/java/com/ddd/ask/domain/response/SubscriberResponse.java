package com.ddd.ask.domain.response;

import com.ddd.ask.domain.query.QueryId;

public class SubscriberResponse extends Response {

    public SubscriberResponse(ResponseId responseId, String text, QueryId queryId) {
        super(responseId, text, queryId);
    }
}
