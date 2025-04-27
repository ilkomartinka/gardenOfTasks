package task;

import java.util.Objects;

public class Task {
    private String taskName;
    private TaskType taskType;
    private String description;


    public Task(String taskName, TaskType taskType, String description) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.description = description;
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
}
