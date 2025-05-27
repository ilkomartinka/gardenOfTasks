package manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.Task;
import model.User;

import java.io.IOException;

public class TaskManager {
    private final ObservableList<Task> tasks;
    private static TaskManager instance;


    public TaskManager() {
        this.tasks = FXCollections.observableArrayList();
    }

    public void addTask(Task task) throws IOException, ClassNotFoundException {
        tasks.add(task);
        UserManager.getInstance().save();
    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void displayTasks(User currentUser, ListView<Task> taskList) {
        taskList.getItems().setAll(currentUser.getTasks());
    }

    public void clearTasks(User currentUser){
        currentUser.getTasks().clear();
    }

    public void completeTask(Task task, User user) throws IOException, ClassNotFoundException {
        if (!task.isDone()) {
            task.setDone(true);
            if (!task.isRewardGiven()) {
                user.addCoins(task.getTaskType().getReward());
                task.setRewardGiven(true);
            }
        }
        UserManager.getInstance().save();
    }

    public void uncompleteTask(Task task, User user) throws IOException, ClassNotFoundException {
        if (task.isDone()) {
            task.setDone(false);
            UserManager.getInstance().save();
        }
    }
    public int getCompletedTasksCount(User user) {
        int count = 0;
        for (Task t : user.getTasks()) {
            if (t.isDone()) {
                count++;
            }
        }
        return count;
    }
}