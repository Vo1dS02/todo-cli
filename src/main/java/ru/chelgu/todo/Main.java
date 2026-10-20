package ru.chelgu.todo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Менеджер задач ======");
        boolean cheackClose = true;
        while (cheackClose == true) {
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все");
            System.out.println("3. Отметить выполнение");
            System.out.println("4. Удалить");
            System.out.println("5. Выход");
            System.out.print("Выберите пункт: ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    System.out.println("");
                    System.out.println("Введите текст задачи: ");
                    System.out.println("Задача добавлена");
                    System.out.println("");
                    break;
                case "2":
                    System.out.println("test");
                    break;
                case "3":
                    System.out.println("Выполнено!");
                    break;
                case "4":
                    System.out.println("Удаление");
                    break;
                case "5":
                    cheackClose = false;
            }

        }
    }

}
