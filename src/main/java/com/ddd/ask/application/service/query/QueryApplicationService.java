package com.ddd.ask.application.service.query;

import com.ddd.ask.application.service.query.commands.AssignQueryCommand;
import com.ddd.ask.application.service.query.commands.ChangeQueryStatusCommand;
import com.ddd.ask.application.service.query.commands.ChangeQueryTitleCommand;
import com.ddd.ask.application.service.query.commands.CreateQueryCommand;
import com.ddd.ask.application.service.query.finders.QueryByIdFinder;
import com.ddd.ask.domain.events.DomainEvent;
import com.ddd.ask.domain.events.DomainEventPublisher;
import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;
import com.ddd.ask.domain.response.SubscriberResponseAddedEvent;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class QueryApplicationService {

    private final QueryRepository queryRepository;

    public QueryApplicationService(QueryRepository queryRepository,
                                   DomainEventPublisher domainEventPublisher) {
        this.queryRepository = queryRepository;
        domainEventPublisher.subscribe(this::handleSubscriberResponseAddedEvent);
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

    public Optional<Query> findQueryById(QueryByIdFinder finder) {
        return queryRepository.find(finder.queryId());
    }

    public void handleSubscriberResponseAddedEvent(DomainEvent event) {
        if (event instanceof SubscriberResponseAddedEvent) {
            SubscriberResponseAddedEvent subscriberResponseAddedEvent = (SubscriberResponseAddedEvent) event;
            final Optional<Query> foundQuery = queryRepository.find(subscriberResponseAddedEvent.queryId());
            foundQuery.ifPresent(query -> {
                query.resetStatusAndUnassign();
                queryRepository.save(query);
            });
        }
    }
}
