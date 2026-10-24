package ru.chelgu.todo.repository;

import ru.chelgu.todo.model.Task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class TaskRepository {
    private final List<Task> tasks = new ArrayList<Task>();
    private long nextId = 1;

    public Task add(String title, LocalDate deadline) {
        Task taskAdd = new Task(nextId, title, deadline);
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

    public void save() {
        List<String> lines = new ArrayList<String>();
        for (Task task : tasks) {
            String addText = String.format("%d;%s;%s", task.getId(), task.getTitle(), task.getDone());
            lines.add(addText);
            Path path = Path.of("tasks.csv");
            try {
                Files.write(path, lines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                System.out.println("Неверная запись в файл");
            }
        }
    }

    public void load() {
        Path path = Path.of("tasks.csv");
        if (Files.exists(path)) {
            try {
                List<String> loadText = Files.readAllLines(path, StandardCharsets.UTF_8);
                for (String str : loadText) {
                    String[] parts = str.split(";");
                    long newId = Long.parseLong(parts[0]);
                    String newMes = parts[1];
                    boolean newDone = Boolean.parseBoolean(parts[2]);

                    if (parts.length > 3 && !parts[3].equals("null")) {
                        LocalDate newDeadline = LocalDate.parse(parts[3]);
                        Task task = new Task(newId, newMes, newDeadline);
                        if (newDone == true) {
                            task.setDone(true);
                        }
                        tasks.add(task);
                        if (task.getId() == nextId) {
                            nextId += 1;
                        }
                    }


                }
            } catch (IOException e) {
                System.out.println("Неверный входной формат данных");
            }
        } else {
            return;
        }
    }
}

