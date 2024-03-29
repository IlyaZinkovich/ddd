package com.ddd.ask.application.service.response;

import com.ddd.ask.application.service.response.commands.AddPracticalLawResponseCommand;
import com.ddd.ask.application.service.response.commands.AddSubscriberResponseCommand;
import com.ddd.ask.application.service.response.finders.QueryResponsesFinder;
import com.ddd.ask.domain.events.DomainEventPublisher;
import com.ddd.ask.domain.response.*;

import java.util.List;

public class ResponseApplicationService {

    private final ResponseRepository responseRepository;
    private final DomainEventPublisher domainEventPublisher;

    public ResponseApplicationService(ResponseRepository responseRepository,
                                      DomainEventPublisher domainEventPublisher) {
        this.responseRepository = responseRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void addSubscriberResponse(AddSubscriberResponseCommand command) {
        final ResponseId responseId = responseRepository.nextIdentity();
        final SubscriberResponse subscriberResponse =
                new SubscriberResponse(responseId, command.responseText(), command.queryId());
        responseRepository.save(subscriberResponse);
        domainEventPublisher.publish(new SubscriberResponseAddedEvent(command.queryId()));
    }

    public void addPracticalLawResponse(AddPracticalLawResponseCommand command) {
        final ResponseId responseId = responseRepository.nextIdentity();
        final PracticalLawResponse practicalLawResponse =
                new PracticalLawResponse(responseId, command.responseText(), command.queryId());
        responseRepository.save(practicalLawResponse);
    }

    public List<Response> findResponsesByQuery(QueryResponsesFinder finder) {
        return responseRepository.find(finder.queryId());
    }
}
