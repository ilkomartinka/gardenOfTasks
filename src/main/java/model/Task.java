package model;

import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private final String taskName;
    private TaskType taskType;
    private final String description;
    private boolean done;


    public Task(String taskName, TaskType taskType, String description) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.description = description;
        this.done = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taskName);
    }

    @Override
    public String toString() {
        CheckBox checkBox = new CheckBox();
        return  checkBox + taskName + " " + taskType;
    }
}
