package org.example.gardenoftasks;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import task.Task;
import task.TaskManager;
import task.TaskType;

import java.io.IOException;

public class AddTaskController {
    private final TaskManager tm;
    Stage stage = new Stage();

    public AddTaskController() {
        this.tm = new TaskManager();
    }

    @FXML
    private JFXTextArea descriptionTextArea;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXTextField taskTextField;

    @FXML
    private JFXComboBox<TaskType> taskTypeComboBox;


    @FXML
    public void initialize() {
        taskTypeComboBox.getItems().addAll(TaskType.values());
    }


    @FXML
    void cancel() throws IOException {
        stage.close();
    }
    @FXML
    public void saveTask(ActionEvent actionEvent) {
        saveBtn.setOnAction(event -> {
        Task task = new Task(taskTextField.getText(), taskTypeComboBox.getValue(), descriptionTextArea.getText());
        tm.addTask(task);
        taskTextField.clear();
        descriptionTextArea.clear();
        stage.close();
    });
    }
}

