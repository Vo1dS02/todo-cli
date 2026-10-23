package ru.chelgu.todo.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.chelgu.todo.TaskRepositoryTest;
import org.junit.jupiter.api.Test;
import ru.chelgu.todo.model.Task;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    private TaskService service;
    @BeforeEach
    void setUp() {
        TaskRepositoryTest repoTest = new TaskRepositoryTest();
        service = new TaskService(repoTest);
    }


    @Test
    public void addTask_shouldIncreaseTaskCount() {
        assertEquals(0, service.getAllTasks().size()); // Проверка что список задач пустой

        service.addTask("Ебануть Нагуманову");

        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    public void removeTaskTest() {
        Task task = service.addTask("OKAK");
        long id = task.getId();
        boolean isRemoved = service.removeTask(id);

        assertEquals(0, service.getAllTasks().size());
        assertTrue(isRemoved);
    }

    @Test
    public void getAllTaskTest() {
        Task firstTestTask = service.addTask("pypypy");
        Task secondTestTask = service.addTask("emae");

        assertEquals(2, service.getAllTasks().size());
    }

    @Test
    public void completeTaskTest() {
        Task testTask = service.addTask("123");
        long id = testTask.getId();
        boolean testMarkDone = service.completeTask(id);
        assertTrue(testMarkDone);
    }

    @Test
    public void littleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(""));
    }

    @Test
    public void notRealIdTest() {
        boolean removeIdTest = service.removeTask(999);
        assertFalse(removeIdTest);
        boolean markDoneTest = service.completeTask(999);
        assertFalse(markDoneTest);
    }

}
