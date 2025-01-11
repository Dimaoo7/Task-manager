package manager.dima;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager implements Serializable{
    public List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    //Добавление задачи
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Задача успешно добавлена");
    }

    //Показ задач
    public void showTasks() {
            if (tasks.isEmpty() || tasks == null) {
                System.out.println("Список задач пуст.");
            } else {
                tasks.stream().sorted(Comparator.comparing(Task::getDeadLine))
                        .forEach(System.out::println);
        }
    }

    //Обновление задач
    public void updateTask(int id, String newName, String newAbout, LocalDate newDeadLine) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        task -> {
                            task.setName(newName);
                            task.setAbout(newAbout);
                            task.setDeadLine(newDeadLine);
                            System.out.println("Задача обновлена: " + task);
                        },
                        () -> System.out.println("Задача с ID " + id + " не найдена.")
                );
    }

    //Удаление задач
    public void deleteTask(int id) {
        Task taskToRemove = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);

        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Задача успешно удалена " + taskToRemove);
        }else {
            System.out.println("Задача с id "  + id + " не найдена.");
        }
    }

    //Сохранение задач в файл сериализация
    public void saveTasksToFile(String fileName) {
        try (FileOutputStream fileOutput = new FileOutputStream(fileName);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {

            objectOutput.writeObject(tasks);
            System.out.println("Задачи успешно сохранены в файл: " + fileName);

        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    //Загрузка задач с файла
    public void loadTasksFromFile(String fileName) {
        try (FileInputStream fileInput = new FileInputStream(fileName);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {

            tasks = (List<Task>) objectInput.readObject();
            System.out.println("Задачи успешно загружены из файла: " + fileName);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }
    }

    //Найти задачу с ближайшим дедлайном
    public void findTaskWithNearestDeadline() {
        tasks.stream()
                .min(Comparator.comparing(Task::getDeadLine))
                .ifPresentOrElse(
                        task -> System.out.println("Задача с ближайшим дедлайном: " + task),
                        () -> System.out.println("Список задач пуст, ближайших дедлайнов нет.")
                );
    }


    //Отсортировать задачи по имени
    public void sortTaskByName() {
        tasks.stream().sorted(Comparator.comparing(Task::getName))
                .forEach(System.out::println);
    }


    //Вывести не выполненые задачи
    public void showUncompletedTask() {
        tasks.stream().filter(task -> !task.getDone())
                .forEach(System.out::println);
    }


    //Задачи с дедлайном через 3 дня
    public void deadlineClose() {
        LocalDate today = LocalDate.now();
        tasks.stream()
                .filter(task -> task.getDeadLine().equals(today.plusDays(3)))
                .forEach(System.out::println);
    }


    //Метод пометки задачи как выполненной
    public void markTaskAsDone(int id) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        task -> {
                            task.markAsDone();
                            System.out.println("Задача " + task.getName() + " помечена как выполненная.");
                        },
                        () -> System.out.println("Задача с ID " + id + " не найдена.")
                );
    }

    //Метод подсчёта задач с установленными дедлайнами
    public long countTasksWithDeadlines() {
        return tasks.stream()
                .filter(task -> task.getDeadLine() != null)
                .count();
    }

}



































