package manager.dima;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
                for (Task task : tasks) {
                System.out.println(task);
            }

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
}



































