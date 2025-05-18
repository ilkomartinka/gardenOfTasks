package org.example.gardenoftasks;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Task;
import manager.TaskManager;
import model.TaskType;
import model.User;


public class AddTaskController {
    private User currentUser;

    @FXML
    private JFXTextArea descriptionTextArea;


    @FXML
    private JFXTextField taskTextField;

    @FXML
    private JFXComboBox<TaskType> taskTypeComboBox;

    @FXML
    private Text massageText;


    @FXML
    public void initialize() {
        taskTypeComboBox.getItems().addAll(TaskType.values());
    }


    @FXML
    void closeWindow() {
        Stage currentStage = (Stage) massageText.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void saveTask() {
        Task newTask = new Task(taskTextField.getText(), taskTypeComboBox.getValue(), descriptionTextArea.getText());
        currentUser.addTask(newTask);
        closeWindow();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}

