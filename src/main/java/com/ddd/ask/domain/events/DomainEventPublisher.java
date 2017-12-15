package com.ddd.ask.domain.events;

import java.util.List;

public interface DomainEventPublisher {

    void subscribe(DomainEventSubscriber subscriber);

    void publish(DomainEvent... events);

    List<DomainEvent> log();
}
