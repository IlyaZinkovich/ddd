package com.ddd.ask.infrastructure.events;

import com.ddd.ask.domain.events.DomainEvent;
import com.ddd.ask.domain.events.DomainEventPublisher;
import com.ddd.ask.domain.events.DomainEventSubscriber;

import java.util.*;

public class InMemoryDomainEventPublisher implements DomainEventPublisher {

    private final Set<DomainEventSubscriber> subscribers = new HashSet<>();

    private final List<DomainEvent> log = new ArrayList<>();

    @Override
    public void subscribe(DomainEventSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void publish(DomainEvent... events) {
        log.addAll(Arrays.asList(events));
        subscribers.forEach(subscriber -> Arrays.stream(events).forEach(subscriber::handleEvent));
    }

    @Override
    public List<DomainEvent> log() {
        return log;
    }
}
