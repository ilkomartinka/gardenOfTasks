package org.example.gardenoftasks;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Task;
import manager.TaskManager;
import model.TaskType;


public class AddTaskController {
    private TaskManager taskManager = TaskManager.getInstance();
    private TaskController mainController;

    public void setMainController(TaskController mainController) {
        this.mainController = mainController;
    }

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
        taskManager.addTask(newTask);
       //
        closeWindow();
    }

}

