package manager.dima;

import java.time.LocalDate;

public class TaskManagerApp {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Добавляем задачи
        manager.addTask(new Task(1, "Сделать домашнее задание", "Завершить задачу по сериализации", LocalDate.of(2025, 1, 15)));
        manager.addTask(new Task(2, "Купить продукты", "Хлеб, молоко, сыр", LocalDate.of(2025, 1, 10)));
        manager.addTask(new Task(3, "Позвонить другу", "Обсудить планы на выходные", LocalDate.of(2025, 1, 12)));

        System.out.println("Список задач перед сохранием:");
        manager.showTasks();

        String fileName = "tasks.dat";
        manager.saveTasksToFile(fileName);

        TaskManager newManager = new TaskManager();
        newManager.loadTasksFromFile(fileName);

        System.out.println("Список задач после загрузки:");
        newManager.showTasks();
    }
}