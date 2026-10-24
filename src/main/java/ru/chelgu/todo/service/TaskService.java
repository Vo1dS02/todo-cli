package ru.chelgu.todo.service;

import ru.chelgu.todo.model.Task;
import ru.chelgu.todo.repository.TaskRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task addTask(String title, LocalDate deadline) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("текст задачи не может быть пустым");
        } else {
            Task newTask = repo.add(title, deadline);
            repo.save();
            return newTask;
        }
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public boolean completeTask(long id) {
        boolean success = repo.markDone(id);
        if (success) {
            repo.save();
        }
        return success;
    }

    public boolean removeTask(long id) {
        boolean success = repo.delete(id);
        if (success) {
            repo.save();
        }
        return success;
    }
}
