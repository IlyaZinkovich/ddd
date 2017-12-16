package com.ddd.ask.domain.subscriber;

import java.util.Objects;

public class SubscriberId {

    private String id;

    public SubscriberId(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    private SubscriberId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriberId that = (SubscriberId) o;
        return Objects.equals(id, that.id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
