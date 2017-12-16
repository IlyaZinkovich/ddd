package com.ddd.ask.application;

import com.ddd.ask.application.service.query.QueryApplicationService;
import com.ddd.ask.application.service.query.commands.AssignQueryCommand;
import com.ddd.ask.application.service.query.commands.ChangeQueryStatusCommand;
import com.ddd.ask.application.service.query.commands.ChangeQueryTitleCommand;
import com.ddd.ask.application.service.query.commands.CreateQueryCommand;
import com.ddd.ask.application.service.query.finders.QueryByIdFinder;
import com.ddd.ask.application.service.response.ResponseApplicationService;
import com.ddd.ask.application.service.response.commands.AddPracticalLawResponseCommand;
import com.ddd.ask.application.service.response.commands.AddSubscriberResponseCommand;
import com.ddd.ask.application.service.response.finders.QueryResponsesFinder;
import com.ddd.ask.domain.editor.EditorId;
import com.ddd.ask.domain.events.DomainEventPublisher;
import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;
import com.ddd.ask.domain.query.QueryStatus;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.response.*;
import com.ddd.ask.domain.subscriber.SubscriberId;
import com.ddd.ask.infrastructure.events.InMemoryDomainEventPublisher;
import com.ddd.ask.infrastructure.query.InMemoryQueryRepository;
import com.ddd.ask.infrastructure.response.InMemoryResponseRepository;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AskApplicationInMemoryIntegrationTest {

    @Test
    public void integrationTest() {
        final DomainEventPublisher domainEventPublisher = new InMemoryDomainEventPublisher();
        final QueryRepository queryRepository = new InMemoryQueryRepository(1210000);
        final ResponseRepository responseRepository = new InMemoryResponseRepository();
        final QueryApplicationService queryApplicationService = new QueryApplicationService(queryRepository, domainEventPublisher);
        final ResponseApplicationService responseApplicationService = new ResponseApplicationService(responseRepository, domainEventPublisher);

        queryApplicationService.createQuery(new CreateQueryCommand(new SubscriberId("subscriberId"), new Question("question")));
        queryApplicationService.changeQueryTitle(new ChangeQueryTitleCommand(new QueryId("a-121-0001"), "title"));
        queryApplicationService.changeQueryStatus(new ChangeQueryStatusCommand(new QueryId("a-121-0001"), QueryStatus.WORKING_ON_ANSWER));
        queryApplicationService.assignQuery(new AssignQueryCommand(new QueryId("a-121-0001"), new EditorId("editor")));
        responseApplicationService.addPracticalLawResponse(new AddPracticalLawResponseCommand(new QueryId("a-121-0001"), "plResponse"));
        responseApplicationService.addSubscriberResponse(new AddSubscriberResponseCommand(new QueryId("a-121-0001"), "subscriberResponse"));
        final Optional<Query> query = queryApplicationService.findQueryById(new QueryByIdFinder(new QueryId("a-121-0001")));
        assertEquals(Optional.of("title"), query.map(Query::title));
        assertEquals(Optional.of(QueryStatus.NOT_STARTED), query.map(Query::status));
        assertEquals(Optional.of(new SubscriberId("subscriberId")), query.map(Query::subscriberId));
        assertEquals(Optional.empty(), query.flatMap(Query::assigneeId));
        assertEquals(Optional.of(new Question("question")), query.map(Query::question));
        final List<Response> responsesByQuery = responseApplicationService.findResponsesByQuery(new QueryResponsesFinder(new QueryId("a-121-0001")));
        assertTrue(responsesByQuery.contains(new PracticalLawResponse(new ResponseId(1L), "plResponse", new QueryId("a-121-0001"))));
        assertTrue(responsesByQuery.contains(new SubscriberResponse(new ResponseId(2L), "subscriberResponse", new QueryId("a-121-0001"))));
    }
}
