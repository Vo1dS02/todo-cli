package ru.chelgu.todo.model;

import java.text.AttributedCharacterIterator;

public class Task {
    private long id;
    private String title;
    private boolean done;

    public Task() {
        this.done = done;
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }
    public boolean getDone() {
        return done;
    }

    public String getTitle() {
        return title;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("%s - #%d - $s", done ? "[x]" : "[ ]", id, title);
    }
}
