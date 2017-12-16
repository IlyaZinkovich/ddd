package com.ddd.ask.domain.query;

import java.util.Objects;

public class QueryId {

    private String resourceId;

    public QueryId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String resourceId() {
        return resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueryId queryId = (QueryId) o;
        return Objects.equals(resourceId, queryId.resourceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId);
    }
}
