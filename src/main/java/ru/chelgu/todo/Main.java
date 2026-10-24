package ru.chelgu.todo;

import ru.chelgu.todo.model.Task;
import ru.chelgu.todo.repository.TaskRepository;
import ru.chelgu.todo.service.TaskService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        TaskRepository repo = new TaskRepository();
        TaskService service = new TaskService(repo);
        repo.load();
        System.out.println("===== Менеджер задач ======");
        boolean cheackClose = true;
        while (cheackClose == true) {
            System.out.print("1. Добавить задачу " +
                    "\n2. Показать все " +
                    "\n3. Отметить выполнение " +
                    "\n4. Удалить " +
                    "\n5. Выход " +
                    "\nВыберите пункт: ");
            String userInput = scanner.nextLine();
            System.out.println("");
            switch (userInput) {
                case "1":
                    System.out.print("Введите текст задачи: ");

                    String title = scanner.nextLine();
                    System.out.println("");
                    if (title.isEmpty()) {
                        System.out.println("Ошибка: текст задачи не может быть пустым");
                    } else {
                        System.out.println("Выберете дедлайн: ");
                        System.out.println("1. На сегодня");
                        System.out.println("2. На завтра");
                        System.out.println("3. Через неделю");
                        System.out.println("4. Через месяц");
                        System.out.println("5. Ввести дедлайн вручную");
                        System.out.println("6. без дедлайна");
                        System.out.println("");
                        System.out.print("Ваш выбор: ");
                        try {
                            String deadlineChoice = scanner.nextLine();
                            LocalDate deadline = null;
                            switch (deadlineChoice) {
                                case "1": {
                                    deadline = LocalDate.now();
                                    service.addTask(title, deadline);
                                } break;
                                case "2": {
                                    deadline = LocalDate.now().plusDays(1);
                                    service.addTask(title, deadline);
                                } break;
                                case "3": {
                                    deadline = LocalDate.now().plusWeeks(1);
                                    service.addTask(title, deadline);
                                } break;
                                case "4": {
                                    deadline = LocalDate.now().plusMonths(1);
                                    service.addTask(title, deadline);
                                } break;
                                case "5": {
                                    System.out.print("Введите дату в формате ГГГГ-ММ-ДД: ");
                                    try {
                                        String deadlineInput = scanner.nextLine();
                                        if (deadlineInput != null) {
                                            service.addTask(title, LocalDate.parse(deadlineInput));
                                            System.out.println("Задача добавлена!");
                                            System.out.println("");
                                        }
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Неверный формат дедлайна");
                                    }
                                    break;

                                }
                                case "6": deadline = null;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Неверный входной формат данных");
                        }
                        break;
                        }


                case "2":
                    for (Task t : service.getAllTasks()) {
                        System.out.println(t);
                    }
                    System.out.println("");
                    break;
                case "3":
                    // repo.markDone();
                    System.out.print("Введите ID: ");
                    try {
                        String stringId = scanner.nextLine();
                        long id = Long.parseLong(stringId);
                        boolean success = service.completeTask(id);
                        if (success) {
                            System.out.println("Выполнено!");
                            System.out.println("");
                        }
                        else {
                            System.out.println("Задача с таким ID не найдена!");
                            System.out.println("");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный формат ID");
                        System.out.println("");
                    }
                    break;

                case "4":
                    System.out.print("Введите ID: ");
                    try {
                        String idDel = scanner.nextLine();
                        long longIdDel = Long.parseLong(idDel);
                        boolean successDel = service.removeTask(longIdDel);
                        if (successDel) {
                            System.out.println("Удалено!");
                            System.out.println("");
                        }
                        else {
                            System.out.println("Задача с таким ID не найдена!");
                            System.out.println("");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный формат ID");
                        System.out.println("");
                    }
                    break;

                case "5":
                    cheackClose = false;
            }

        }

    }

}