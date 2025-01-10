package manager.dima;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setName(newName);
                task.setAbout(newAbout);
                task.setDeadLine(newDeadLine);
                System.out.println("Задача обновалена " + task);
            }
        }
        System.out.println("Задача с ID " + id + " не найдена.");
    }

    //Удаление задач
    public void deleteTask(int id) {

        tasks.stream();

            boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            System.out.println("Задача успешно удалена.");
        } else {
            System.out.println("Задача c id" + id + " не найдена.");
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

    //Найти задачи с ближайшим дедлайном
    public void findTaskNearDied(List<Task> tasks) {
        tasks.stream().sorted(Comparator.comparing(Task::getDeadLine))
                .limit(1)
                .forEach(System.out::println);
    }

    //Отсортировать задачи по имени
    public void sortTaskByName(List<Task> tasks) {
        tasks.stream().sorted(Comparator.comparing(Task::getName))
                .forEach(System.out::println);
    }


    //Вывести не выполненые задачи
    public void showUncompletedTask() {
        tasks.stream().filter(task -> !task.getDone())
                .forEach(System.out::println);
    }

}



































