package manager.dima;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Serializable {
    private int id;
    private String name;
    private String about;

    private enum state {New, IN_PROGRESS, DONE}

    private LocalDate deadLine;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", deadLine=" + deadLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(about, task.about) && Objects.equals(deadLine, task.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, about, deadLine);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public Task(int id, String name, String about, LocalDate deadLine) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.deadLine = deadLine;
    }
}
