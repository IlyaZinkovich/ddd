package com.ddd.ask.domain.question;

import java.util.Objects;

public class Question {

    private String text;

    private Question() {
    }

    public Question(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;
        return Objects.equals(text, question.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
