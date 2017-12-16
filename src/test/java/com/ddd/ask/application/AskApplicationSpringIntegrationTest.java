package com.ddd.ask.application;

import com.ddd.ask.application.configuration.ApplicationConfiguration;
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
import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryStatus;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.response.PracticalLawResponse;
import com.ddd.ask.domain.response.Response;
import com.ddd.ask.domain.response.ResponseId;
import com.ddd.ask.domain.response.SubscriberResponse;
import com.ddd.ask.domain.subscriber.SubscriberId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Transactional
public class AskApplicationSpringIntegrationTest {

    @Autowired
    private QueryApplicationService queryApplicationService;

    @Autowired
    private ResponseApplicationService responseApplicationService;

    @Test
    public void integrationTest() {
        queryApplicationService.createQuery(new CreateQueryCommand(new SubscriberId("subscriberId"), new Question("question")));
        queryApplicationService.changeQueryTitle(new ChangeQueryTitleCommand(new QueryId("a-121-0000"), "title"));
        queryApplicationService.changeQueryStatus(new ChangeQueryStatusCommand(new QueryId("a-121-0000"), QueryStatus.WORKING_ON_ANSWER));
        queryApplicationService.assignQuery(new AssignQueryCommand(new QueryId("a-121-0000"), new EditorId("editor")));
        responseApplicationService.addPracticalLawResponse(new AddPracticalLawResponseCommand(new QueryId("a-121-0000"), "plResponse"));
        responseApplicationService.addSubscriberResponse(new AddSubscriberResponseCommand(new QueryId("a-121-0000"), "subscriberResponse"));
        final Optional<Query> query = queryApplicationService.findQueryById(new QueryByIdFinder(new QueryId("a-121-0000")));
        assertEquals(Optional.of("title"), query.map(Query::title));
        assertEquals(Optional.of(QueryStatus.NOT_STARTED), query.map(Query::status));
        assertEquals(Optional.of(new SubscriberId("subscriberId")), query.map(Query::subscriberId));
        assertEquals(Optional.empty(), query.flatMap(Query::assigneeId));
        assertEquals(Optional.of(new Question("question")), query.map(Query::question));
        final List<Response> responsesByQuery = responseApplicationService.findResponsesByQuery(new QueryResponsesFinder(new QueryId("a-121-0000")));
        assertTrue(responsesByQuery.contains(new PracticalLawResponse(new ResponseId(1L), "plResponse", new QueryId("a-121-0000"))));
        assertTrue(responsesByQuery.contains(new SubscriberResponse(new ResponseId(2L), "subscriberResponse", new QueryId("a-121-0000"))));
    }
}
