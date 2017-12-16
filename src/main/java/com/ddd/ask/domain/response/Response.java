package com.ddd.ask.domain.response;

import com.ddd.ask.domain.query.QueryId;

import java.time.Instant;
import java.util.Objects;

public abstract class Response {

    private ResponseId id;
    private Instant modificationDate;
    private String text;
    private QueryId queryId;

    public Response(ResponseId id, String text, QueryId queryId) {
        this.id = id;
        this.queryId = queryId;
        this.modificationDate = Instant.now();
        this.text = text;
    }

    public ResponseId id() {
        return id;
    }

    public Instant modificationDate() {
        return modificationDate;
    }

    public String text() {
        return text;
    }

    public QueryId queryId() {
        return queryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;
        return Objects.equals(id, response.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.hashCode());
    }
}
