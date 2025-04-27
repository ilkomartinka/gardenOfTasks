package org.example.gardenoftasks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    void addTask(ActionEvent event) throws IOException {
        switcher.switchToScene(stage,"addTaskPage.fxml");
    }


    @FXML
    public void initialize() {

    }
}


