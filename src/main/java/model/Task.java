package model;

import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a task created by the user.
 * Each task has a name, type, description, completion status, and reward status.
 */
public class Task implements Serializable {
    private final String taskName;
    private final TaskType taskType;
    private final String description;
    private boolean done;
    private boolean rewardGiven;

    /**
     * Creates a new Task.
     *
     * @param taskName    the name/title of the task.
     * @param taskType    the type of the task
     * @param description a short description of the task.
     */
    public Task(String taskName, TaskType taskType, String description) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.description = description;
        this.done = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the type of the task.
     *
     * @return the task type.
     */

    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done true if completed, false otherwise.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Marks whether the reward for this task has been given.
     *
     * @param rewardGiven true if the user already received a reward.
     */
    public void setRewardGiven(boolean rewardGiven) {
        this.rewardGiven = rewardGiven;
    }

    /**
     * Returns whether the reward has been given for this task.
     *
     * @return true if reward was given, false otherwise.
     */
    public boolean isRewardGiven() {
        return rewardGiven;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if completed.
     */
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
        return checkBox + taskName + " " + taskType;
    }

}
