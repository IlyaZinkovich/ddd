package com.ddd.ask.domain.response;

import com.ddd.ask.domain.query.QueryId;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository {

    ResponseId nextIdentity();

    void save(Response response);

    Optional<Response> find(ResponseId id);

    List<Response> find(QueryId queryId);
}
