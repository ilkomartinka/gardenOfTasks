package org.example.gardenoftasks;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class Switcher {
    // Switch scene using given FXML file and Stage
    public  void switchToSceneUsingStage(Stage stage, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Switcher.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
    // Switch scene using ActionEvent
    public void switchToSceneUsingEvent(javafx.event.ActionEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switchToSceneUsingStage(stage, fxmlPath);
    }
}
