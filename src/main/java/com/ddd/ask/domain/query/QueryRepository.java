package com.ddd.ask.domain.query;

import java.util.Optional;

public interface QueryRepository {

    QueryId nextIdentity();

    void save(Query query);

    Optional<Query> find(QueryId id);
}
