package com.ddd.ask.application.service;

import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.query.QueryRepository;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;
import com.ddd.ask.infrastructure.InMemoryQueryRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryApplicationServiceTest {

    @Test
    public void testQueryCreation() {
        int resourceIdSeed = 1210000;
        QueryRepository queryRepository = new InMemoryQueryRepository(resourceIdSeed);
        QueryApplicationService queryApplicationService = new QueryApplicationService(queryRepository);

        String subscriberId = "subscriberId";
        String questionText = "questionText";
        queryApplicationService.createQuery(new CreateQueryCommand(new SubscriberId(subscriberId), new Question(questionText)));

        Optional<Query> query = queryRepository.find(new QueryId("a-121-0001"));
        assertTrue(query.isPresent());
        assertEquals(Optional.of(questionText), query.map(Query::question).map(Question::text));
        assertEquals(Optional.of(subscriberId), query.map(Query::subscriberId).map(SubscriberId::id));
    }
}
