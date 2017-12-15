package com.ddd.ask.application.service;

import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;
import com.ddd.ask.infrastructure.InMemoryQueryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryApplicationServiceTest {

    private static final int RESOURCE_ID_SEED = 1210000;
    private QueryRepository queryRepository;
    private QueryApplicationService queryApplicationService;

    @Before
    public void setup() {
        queryRepository = new InMemoryQueryRepository(RESOURCE_ID_SEED);
        queryApplicationService = new QueryApplicationService(queryRepository);
    }

    @Test
    public void testQueryCreation() {
        String subscriberId = "subscriberId";
        String questionText = "questionText";

        queryApplicationService.createQuery(new CreateQueryCommand(new SubscriberId(subscriberId), new Question(questionText)));

        Optional<Query> query = queryRepository.find(new QueryId("a-121-0001"));
        assertTrue(query.isPresent());
        assertEquals(Optional.of(questionText), query.map(Query::question).map(Question::text));
        assertEquals(Optional.of(subscriberId), query.map(Query::subscriberId).map(SubscriberId::id));
    }

    @Test
    public void testQueryTitleChange() {
        QueryId queryId = new QueryId("a-121-0001");
        String newTitle = "new title";
        queryRepository.save(new Query(queryId, new SubscriberId("testSubscriberId"), new Question("testQuestion")));

        queryApplicationService.changeQueryTitle(new ChangeQueryTitleCommand(queryId, newTitle));

        Optional<Query> query = queryRepository.find(queryId);
        assertTrue(query.isPresent());
        assertEquals(Optional.of(newTitle), query.map(Query::title));
    }
}
