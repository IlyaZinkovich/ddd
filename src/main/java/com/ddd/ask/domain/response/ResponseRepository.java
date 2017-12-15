package com.ddd.ask.domain.response;

import java.util.Optional;

public interface ResponseRepository {

    ResponseId nextIdentity();

    void save(Response response);

    Optional<Response> find(ResponseId id);
}
