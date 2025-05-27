package org.example.gardenOfTasks;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import manager.UserManager;
import model.Task;
import manager.TaskManager;
import model.TaskType;
import model.User;

import java.io.IOException;


public class AddTaskController {
    private User currentUser;
    private final TaskManager taskManager;

    public AddTaskController() throws IOException, ClassNotFoundException {
        taskManager= TaskManager.getInstance();
    }

    @FXML
    private JFXTextArea descriptionTextArea;


    @FXML
    private JFXTextField taskTextField;

    @FXML
    private JFXComboBox<TaskType> taskTypeComboBox;

    @FXML
    private Text messageText;


    @FXML
    public void initialize() {
        taskTypeComboBox.getItems().addAll(TaskType.values());
    }


    @FXML
    void closeWindow() {
        Stage currentStage = (Stage) messageText.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void saveTask() throws IOException, ClassNotFoundException {
        Task newTask = new Task(taskTextField.getText(), taskTypeComboBox.getValue(), descriptionTextArea.getText());
        if(taskTypeComboBox.getValue() != null){
            if(!taskTextField.getText().isEmpty()){
                currentUser.addTask(newTask);
                taskManager.addTask(newTask);
                UserManager.getInstance().save();
                closeWindow();
            }else{
                messageText.setText("Please enter a task name");
            }
        }else{
            messageText.setText("Please select a task type");
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}