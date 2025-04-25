package task;

import java.util.Objects;

public class Task {
    private String taskName;
    private TaskType taskType;


    public Task(String taskName, TaskType taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskType getTaskType() {
        return taskType;
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
