package org.example.gardenoftasks;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ShopController {
    private Switcher switcher;

    @FXML
    private JFXButton buyBtn;

    @FXML
    private HBox chosenPlantCard;

    @FXML
    private Button gardenBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private ImageView plantImage;

    @FXML
    private Label plantNameLabel;

    @FXML
    private Label plantPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private Button shopBtn;

    @FXML
    private ScrollPane scroll;

    public ShopController() {
        switcher = new Switcher();
    }

    @FXML
    void goToGarden() throws IOException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "garden.fxml");
    }

    @FXML
    void goToHome() throws IOException {
        switcher.switchToScene((Stage) homeBtn.getScene().getWindow(), "mainPage.fxml");
    }



}
