package com.ddd.ask.application.service.query;

import com.ddd.ask.application.service.query.commands.*;
import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;

import java.util.Optional;

public class QueryApplicationService {

    private final QueryRepository queryRepository;

    public QueryApplicationService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public void createQuery(CreateQueryCommand command) {
        QueryId queryId = queryRepository.nextIdentity();
        Query query = new Query(queryId, command.subscriberId(), command.question());
        queryRepository.save(query);
    }

    public void changeQueryTitle(ChangeQueryTitleCommand command) {
        Optional<Query> foundQuery = queryRepository.find(command.queryId());
        foundQuery.ifPresent(query -> {
            query.changeTitle(command.title());
            queryRepository.save(query);
        });
    }

    public void assignQuery(AssignQueryCommand command) {
        Optional<Query> foundQuery = queryRepository.find(command.queryId());
        foundQuery.ifPresent(query -> {
            query.assign(command.editorId());
            queryRepository.save(query);
        });
    }

    public void changeQueryStatus(ChangeQueryStatusCommand command) {
        Optional<Query> foundQuery = queryRepository.find(command.queryId());
        foundQuery.ifPresent(query -> {
            query.changeStatus(command.status());
            queryRepository.save(query);
        });
    }

    public void addSubscriberResponse(AddSubscriberResponseCommand command) {
        Optional<Query> foundQuery = queryRepository.find(command.queryId());
        foundQuery.ifPresent(query -> {
            query.addResponse(command.response());
            queryRepository.save(query);
        });
    }
}
