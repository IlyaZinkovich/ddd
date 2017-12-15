package com.ddd.ask.domain.events;

public interface DomainEventSubscriber {

    void handleEvent(DomainEvent event);
}
