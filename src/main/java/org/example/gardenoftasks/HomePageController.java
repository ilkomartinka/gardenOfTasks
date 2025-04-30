package org.example.gardenoftasks;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import task.Task;
import task.TaskManager;

import java.io.IOException;

public class HomePageController {
    Switcher switcher = new Switcher();
    Stage stage = new Stage();

    @FXML
    private Button gardenBtn;

    @FXML
    private Button shopBtn;

    @FXML
    private Button toDoBtn;

    @FXML
    private VBox taskList;
    @FXML
    private Text massageText;
    TaskManager taskManager = new TaskManager();

    @FXML
    void addTaskBtn() throws IOException {
        switcher.switchToScene(stage, "addTaskPage.fxml");
        taskManager.displayTask();
        refreshTasks();
    }


    @FXML
    public void initialize() {

    }
    public void refreshTasks() {
        taskManager.displayTask(taskList);
    }
}


