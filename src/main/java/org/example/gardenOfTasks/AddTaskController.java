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

/**
 * Controller for the Add Task window.
 * Allows the user to enter task details and save them to the current user.
 */

public class AddTaskController {
    private User currentUser;
    private final TaskManager taskManager;

    /**
     * Constructor that initializes the TaskManager instance.
     */
    public AddTaskController() {
        taskManager = TaskManager.getInstance();
    }

    @FXML
    private JFXTextArea descriptionTextArea;


    @FXML
    private JFXTextField taskTextField;

    @FXML
    private JFXComboBox<TaskType> taskTypeComboBox;

    @FXML
    private Text messageText;

    /**
     * Initializes the combo box with available task types.
     */
    @FXML
    public void initialize() {
        taskTypeComboBox.getItems().addAll(TaskType.values());
    }

    /**
     * Closes the current window.
     */
    @FXML
    void closeWindow() {
        Stage currentStage = (Stage) messageText.getScene().getWindow();
        currentStage.close();
    }

    /**
     * Validates the input and saves the new task to the user and system.
     * Shows a message if required fields are missing.
     */
    @FXML
    public void saveTask() throws IOException, ClassNotFoundException {
        Task newTask = new Task(taskTextField.getText(), taskTypeComboBox.getValue(), descriptionTextArea.getText());
        if (taskTypeComboBox.getValue() != null) {
            if (!taskTextField.getText().isEmpty()) {
                currentUser.addTask(newTask);
                taskManager.addTask(newTask);
                UserManager.getInstance().save();
                closeWindow();
            } else {
                messageText.setText("Please enter a task name");
            }
        } else {
            messageText.setText("Please select a task type");
        }
    }
    
    
    
    
    
    
    
    
    /**
     * Sets the current user to whom the new task will be added.
     *
     * @param user the current user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}