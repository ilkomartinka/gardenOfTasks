package manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Task;
import model.User;

public class TaskManager {
    private final ObservableList<Task> tasks;
    private static TaskManager instance;

    public TaskManager() {
        this.tasks = FXCollections.observableArrayList();
    }

    public void addTask(Task task) {
        tasks.add(task);
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

    public void clearTasks() {
        tasks.clear();
    }

    public void completeTask(Task task, User user) {
        if (!task.isDone()) {
            task.setDone(true);
            user.addCoins(task.getTaskType().getReward());
        }
    }

    public void uncompleteTask(Task task, User user) {
        if (task.isDone()) {
            task.setDone(false);
            user.removeCoins(task.getTaskType().getReward());
        }
    }


}