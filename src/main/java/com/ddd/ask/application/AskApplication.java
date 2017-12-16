package com.ddd.ask.application;

import com.ddd.ask.application.service.query.QueryApplicationService;
import com.ddd.ask.application.service.query.commands.CreateQueryCommand;
import com.ddd.ask.application.service.query.finders.QueryByIdFinder;
import com.ddd.ask.domain.query.Query;
import com.ddd.ask.domain.query.QueryId;
import com.ddd.ask.domain.question.Question;
import com.ddd.ask.domain.subscriber.SubscriberId;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class AskApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = new SpringApplicationBuilder(AskApplication.class).web(false).run(args);
        final QueryApplicationService queryApplicationService = context.getBean(QueryApplicationService.class);
        queryApplicationService.createQuery(new CreateQueryCommand(new SubscriberId("susbscriberId"), new Question("question")));
        final Optional<Query> query = queryApplicationService.findQueryById(new QueryByIdFinder(new QueryId("a-121-0000")));
        System.out.println(query);
    }
}
