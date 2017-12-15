package com.ddd.ask.infrastructure.response;

import com.ddd.ask.domain.response.Response;
import com.ddd.ask.domain.response.ResponseId;
import com.ddd.ask.domain.response.ResponseRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InMemoryResponseRepository implements ResponseRepository {

    private long idGenerator = 1L;
    private Set<Response> responses = new HashSet<>();

    @Override
    public ResponseId nextIdentity() {
        return new ResponseId(idGenerator++);
    }

    @Override
    public void save(Response response) {
        responses.add(response);
    }

    @Override
    public Optional<Response> find(ResponseId id) {
        return responses.stream().filter(response -> response.id().equals(id)).findAny();
    }
}
