package manager.dima;

import java.time.LocalDate;

public class TaskManagerApp {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Добавляем задачи
        manager.addTask(new Task(1, "Сделать домашнее задание", "Завершить задачу по сериализации", LocalDate.of(2025, 1, 15)));
        manager.addTask(new Task(2, "Купить продукты", "Хлеб, молоко, сыр", LocalDate.of(2025, 1, 10)));
        manager.addTask(new Task(3, "Позвонить другу", "Обсудить планы на выходные", LocalDate.of(2025, 1, 12)));
        manager.addTask(new Task(4, "Позвонить другу", "Обсудить планы на выходные11", LocalDate.of(2025, 1, 13)));

        System.out.println("Список задач перед сохранием:");
        manager.showTasks();

        String fileName = "tasks.dat";
        manager.saveTasksToFile(fileName);

        TaskManager newManager = new TaskManager();
        newManager.loadTasksFromFile(fileName);

        System.out.println("Список задач после загрузки:");
        newManager.showTasks();

        Task task5 = new Task(5, "Задача 1", "Описание задачи 1", LocalDate.now().plusDays(3));
        Task task6 = new Task(6, "Задача 2", "Описание задачи 2", null); // Без дедлайна
        Task task7 = new Task(7, "Задача 3", "Описание задачи 3", LocalDate.now().plusDays(5));

        manager.addTask(task5);
        manager.addTask(task6);
        manager.addTask(task7);

        long count = manager.countTasksWithDeadlines();
        System.out.println("Количество задач с дедлайнами: " + count);

    }
}