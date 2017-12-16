package com.ddd.ask.domain.query;

import java.io.Serializable;
import java.util.Objects;

public class QueryId implements Serializable {

    private static final long serialVersionUID = -5995669828598841751L;

    private String resourceId;

    private QueryId() {
    }

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
