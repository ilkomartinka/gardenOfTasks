package task;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public void displayTask(VBox vbox) {
        for (Task task : tasks) {
            HBox item = createTask(task);
            vbox.getChildren().add(item);
        }
    }

    public HBox createTask(Task task) {
        HBox taskItem = new HBox(10);
        CheckBox checkBox = new CheckBox();
        Label label = new Label(task.getTaskName());
        if(task.getTaskType() == null){

        }
        if (task.getTaskType().equals(TaskType.COMPLETED)) {
            label.setStyle("-fx-strikethrough: true;");
            checkBox.setSelected(true);
        }
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                task.setTaskType(TaskType.COMPLETED);
                label.setStyle("-fx-font-size: 16px; -fx-strikethrough: true;");
            } else {
                task.setTaskType(TaskType.TODO);
                label.setStyle("-fx-font-size: 16px; -fx-strikethrough: false;");
            }
        });
        return taskItem;
    }
}
