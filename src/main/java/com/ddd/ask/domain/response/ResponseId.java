package com.ddd.ask.domain.response;

import java.io.Serializable;
import java.util.Objects;

public class ResponseId implements Serializable {

    private static final long serialVersionUID = 3174871553558256308L;

    private Long id;

    private ResponseId() {
    }

    public ResponseId(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseId that = (ResponseId) o;
        return Objects.equals(id, that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
