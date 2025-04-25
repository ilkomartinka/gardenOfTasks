package task;

import java.util.HashSet;

public class TaskManager {
    private final HashSet<Task> tasks;

    public TaskManager() {
        this.tasks = new HashSet<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public HashSet<Task> getTasks() {
        return tasks;
    }
}
