package com.ddd.ask.domain.response;

import com.ddd.ask.domain.query.QueryId;

public class PracticalLawResponse extends Response {

    public PracticalLawResponse(ResponseId responseId, String text, QueryId queryId) {
        super(responseId, text, queryId);
    }
}
