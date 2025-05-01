package org.example.gardenoftasks;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import task.Task;
import task.TaskManager;
import task.TaskType;
import user.User;

import java.io.IOException;

public class MainController {
    private final Switcher switcher = new Switcher();
    private Stage stage = new Stage();
    private User currentUser;

    @FXML
    private Button gardenBtn;

    @FXML
    private Button shopBtn;

    @FXML
    private Button toDoBtn;
    @FXML
    private JFXListView<Task> taskList;
    @FXML
    private Text massageText;

    private final TaskManager taskManager = TaskManager.getInstance();

    @FXML
    void addTaskBtn() throws IOException {
        switcher.switchToScene(stage, "addTaskPage.fxml");
        taskManager.displayTasks(taskList);
    }


    @FXML
    public void initialize() {
        if (taskList != null) {
            massageText.setVisible(false);
        }
        setupTaskListView();
    }

    private void setupTaskListView() {
        taskList.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setGraphic(null);
                } else {
                    setGraphic(createTask(task));
                }
            }
        });
    }

    private Node createTask(Task task) {
        Label taskTypeLabel = createTaskTypeLabel(task);
        Label titleLabel = createTaskLabel(task);
        JFXCheckBox checkBox = createCheckBox(task, titleLabel);

        HBox taskLine = new HBox(10, checkBox, titleLabel, taskTypeLabel);
        taskLine.setAlignment(Pos.CENTER_LEFT); // set position in the line
        return taskLine;
    }

    private Label createTaskTypeLabel(Task task) {
        Label label = new Label(task.getTaskType().getDescription());
        label.setMinWidth(350);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: gray;");
        label.setAlignment(Pos.BASELINE_RIGHT);
        if(task.isDone()){
            task.setTaskType(TaskType.COMPLETED);
        }
        return label;
    }

    private Label createTaskLabel(Task task) {
        Label label = new Label(task.getTaskName());
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        label.setWrapText(true); // allows text to go to a new line
        label.setAlignment(Pos.CENTER_LEFT);
        if (task.isDone()) {
            label.setStyle("-fx-strikethrough: true;");
        }
        return label;
    }

    private JFXCheckBox createCheckBox(Task task, Label titleLabel) {
        JFXCheckBox checkBox = new JFXCheckBox();
        checkBox.setSelected(task.isDone());
        checkBox.setAlignment(Pos.CENTER_LEFT);
        checkBox.setOnAction(event -> {
            if(checkBox.isSelected()) {
                titleLabel.setStyle("-fx-strikethrough: true");
                task.setTaskType(TaskType.COMPLETED);
            }
            task.setDone(checkBox.isSelected());
        });
        return checkBox;
    }

}
