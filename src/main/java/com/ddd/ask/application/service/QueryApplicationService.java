package com.ddd.ask.application.service;

import com.ddd.ask.domain.editor.EditorId;
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
}
