package com.ddd.ask.application.service;

import com.ddd.ask.application.service.response.ResponseApplicationService;
import com.ddd.ask.application.service.response.commands.AddPracticalLawResponseCommand;
import com.ddd.ask.application.service.response.commands.AddSubscriberResponseCommand;
import com.ddd.ask.application.service.response.finders.QueryResponsesFinder;
import com.ddd.ask.domain.events.DomainEventPublisher;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.response.*;
import com.ddd.ask.infrastructure.events.InMemoryDomainEventPublisher;
import com.ddd.ask.infrastructure.response.InMemoryResponseRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseApplicationServiceTest {

    private ResponseRepository responseRepository;
    private ResponseApplicationService responseApplicationService;
    private DomainEventPublisher domainEventPublisher;

    @Before
    public void setup() {
        responseRepository = new InMemoryResponseRepository();
        domainEventPublisher = new InMemoryDomainEventPublisher();
        responseApplicationService = new ResponseApplicationService(responseRepository, domainEventPublisher);
    }

    @Test
    public void testSubscriberResponseAddedToQuery() {
        final QueryId queryId = new QueryId("a-121-0001");
        final String subscriberResponse = "subscriberResponse";

        responseApplicationService.addSubscriberResponse(new AddSubscriberResponseCommand(queryId, subscriberResponse));

        final Optional<Response> response = responseRepository.find(new ResponseId(1L));
        assertEquals(Optional.of(subscriberResponse), response.map(Response::text));
        final Optional<QueryId> updatedQueryId = domainEventPublisher.log().stream().findAny()
                .map(event -> (SubscriberResponseAddedEvent) event).map(SubscriberResponseAddedEvent::queryId);
        assertEquals(Optional.of(queryId), updatedQueryId);
    }

    @Test
    public void testPracticalLawResponseAddedToQuery() {
        final QueryId queryId = new QueryId("a-121-0001");
        final String plResponse = "plResponse";

        responseApplicationService.addPracticalLawResponse(new AddPracticalLawResponseCommand(queryId, plResponse));

        final Optional<Response> response = responseRepository.find(new ResponseId(1L));
        assertEquals(Optional.of(plResponse), response.map(Response::text));
    }

    @Test
    public void testFindResponsesByQuery() {
        final QueryId queryId = new QueryId("a-121-0001");
        final SubscriberResponse subscriberResponse = new SubscriberResponse(responseRepository.nextIdentity(), "subscriberResponse", queryId);
        responseRepository.save(subscriberResponse);
        final PracticalLawResponse plResponse = new PracticalLawResponse(responseRepository.nextIdentity(), "plResponse", queryId);
        responseRepository.save(plResponse);

        final List<Response> responsesByQuery = responseApplicationService.findResponsesByQuery(new QueryResponsesFinder(queryId));

        assertTrue(responsesByQuery.contains(subscriberResponse));
        assertTrue(responsesByQuery.contains(plResponse));
    }
}
