package com.ddd.ask.domain.editor;

import java.util.Objects;

public class EditorId {

    private String username;

    public EditorId(String username) {
        this.username = username;
    }

    public String username() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EditorId editorId = (EditorId) o;
        return Objects.equals(username, editorId.username());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
