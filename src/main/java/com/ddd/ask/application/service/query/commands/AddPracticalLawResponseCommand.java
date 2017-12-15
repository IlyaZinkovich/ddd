package com.ddd.ask.application.service.query.commands;

import com.ddd.ask.domain.query.PracticalLawResponse;
import com.ddd.ask.domain.query.QueryId;

public class AddPracticalLawResponseCommand {

    private QueryId queryId;
    private PracticalLawResponse practicalLawResponse;

    public AddPracticalLawResponseCommand(QueryId queryId, PracticalLawResponse practicalLawResponse) {
        this.queryId = queryId;
        this.practicalLawResponse = practicalLawResponse;
    }

    public QueryId queryId() {
        return queryId;
    }

    public PracticalLawResponse response() {
        return practicalLawResponse;
    }
}
