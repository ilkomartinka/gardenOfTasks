package manager;

import javafx.scene.control.ListView;
import model.Task;
import model.User;

import java.io.IOException;

/**
 * TaskManager handles task-related actions like adding, completing, and displaying tasks.
 */
public class TaskManager {
    private static TaskManager instance;

    /**
     * Returns the singleton instance of TaskManager.
     *
     * @return instance of TaskManager
     */
    public static TaskManager getInstance() { //Singleton method from https://www.baeldung.com/java-singleton
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    /**
     * Displays the user's tasks in a ListView.
     *
     * @param currentUser the user whose tasks are displayed
     * @param taskList    the ListView to show tasks
     */
    public void displayTasks(User currentUser, ListView<Task> taskList) {
        taskList.getItems().setAll(currentUser.getTasks());
    }

    /**
     * Clears all tasks.
     *
     * @param currentUser the user whose tasks are to be cleared
     */
    public void clearTasks(User currentUser) {
        currentUser.getTasks().clear();
    }

    /**
     * Marks a task as completed and gives the reward if not already given.
     *
     * @param task the task to complete
     * @param user the user who completed the task
     * @throws IOException            if saving fails
     * @throws ClassNotFoundException if user data cannot be loaded
     */
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

    /**
     * Marks a task as not completed.
     *
     * @param task the task to uncomplete
     * @throws IOException            if saving fails
     * @throws ClassNotFoundException if user data cannot be loaded
     */
    public void uncompleteTask(Task task) throws IOException, ClassNotFoundException {
        if (task.isDone()) {
            task.setDone(false);
            UserManager.getInstance().save();
        }
    }

    /**
     * Returns the number of completed tasks.
     *
     * @param user the user whose completed tasks are counted
     * @return number of completed tasks
     */
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