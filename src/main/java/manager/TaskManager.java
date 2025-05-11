package manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Task;

public class TaskManager {
    private final ObservableList<Task> tasks;
    private static TaskManager instance;

    public TaskManager() {
        this.tasks = FXCollections.observableArrayList();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void displayTasks(ListView<Task> taskList) {
        taskList.setItems(tasks);
    }

}