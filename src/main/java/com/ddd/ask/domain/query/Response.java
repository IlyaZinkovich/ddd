package com.ddd.ask.domain.query;

import java.time.Instant;

public interface Response {

    Instant modificationDate();
    String text();
}
