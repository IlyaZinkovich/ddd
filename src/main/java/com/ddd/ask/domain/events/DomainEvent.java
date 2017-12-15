package com.ddd.ask.domain.events;

import java.time.Instant;

public interface DomainEvent {

    int eventVersion();

    Instant occurredOn();
}
