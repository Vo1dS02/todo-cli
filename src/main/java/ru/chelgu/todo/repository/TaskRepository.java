package ru.chelgu.todo.repository;

import ru.chelgu.todo.model.Task;

import java.util.*;

public class TaskRepository {
    private final List<Task> tasks = new ArrayList<Task>();
    private long nextId = 1;

    public Task add(String title) {
        Task taskAdd = new Task(nextId, title);
        tasks.add(taskAdd);
        nextId++;
        return taskAdd;
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    public boolean markDone(long id) {
        for (Task text : tasks) {
            if (id == text.getId()) {
                text.setDone(true);
                return true;
            }
        }
        return false;
    }

    public boolean delete(long id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if (id == iterator.next().getId()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Optional<Task> findById(long id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            if (id == currentTask.getId()) {
                return Optional.of(currentTask);
            }
        }
        return Optional.empty();
    }
}

