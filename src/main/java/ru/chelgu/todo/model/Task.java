package ru.chelgu.todo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements ColorText{
    private long id;
    private String title;
    private boolean done;
    private LocalDate deadline;

    public Task(long id, String title, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
    }

    public long getId() {
        return id;
    }

    public boolean getDone() {
        return done;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getTitle() {
        return title;
    }

    public void setDone(boolean done) {
        this.done = done;
    }



    @Override
    public String toString() {
        String baseInput = String.format("%s - #%d - %s (до: %s) ", done ? "[x]" : "[ ]", id, title, deadline);
        if (!done && deadline != null && (deadline.isEqual(LocalDate.now()) || deadline.isBefore(LocalDate.now()))) {
            return ColorText.RED + baseInput + ColorText.RESET;
        } else if(done == true) {
            return ColorText.GREEN + baseInput + ColorText.RESET;
        }
        else return baseInput;
    }
}
