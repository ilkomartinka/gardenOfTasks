package org.example.gardenoftasks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import task.Task;
import task.TaskManager;
import task.TaskType;

import java.io.IOException;

public class AddTaskController {
    private final TaskManager tm;
    Switcher switcher = new Switcher();
    Stage stage = new Stage();

    public AddTaskController() {
        this.tm = new TaskManager();
    }


    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextArea descriptionTextArea;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXTextField taskTextField;

    @FXML
    private JFXComboBox<TaskType> taskTypeComboBox;

    @FXML
    private VBox taskList;

    @FXML
    public void initialize() {
        taskTypeComboBox.getItems().addAll(TaskType.values());
    }

    @FXML
    void saveTask() throws IOException {
        Task task = new Task(taskTextField.getText(), taskTypeComboBox.getValue(), descriptionTextArea.getText());
        tm.addTask(task);
        tm.displayTask(task,taskList);
        taskTextField.clear();
        descriptionTextArea.clear();
        switcher.closeStage(stage);
    }

    @FXML
    void cancel() throws IOException {
        switcher.closeStage(stage);
    }

}

