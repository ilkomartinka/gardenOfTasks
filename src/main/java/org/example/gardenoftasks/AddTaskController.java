package org.example.gardenoftasks;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import task.Task;
import task.TaskManager;
import task.TaskType;


public class AddTaskController {
    private TaskManager taskManager = TaskManager.getInstance();
    private MainController mainController;

    public void setMainController(MainController mainController) {
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

