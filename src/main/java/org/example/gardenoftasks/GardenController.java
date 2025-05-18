package org.example.gardenoftasks;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Plant;
import model.User;
import util.ViewSwitcher;

import javax.swing.text.Element;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

public class GardenController {


    @FXML
    private Button homeBtn;

    @FXML
    private Button shopBtn;

    private final ViewSwitcher switcher;
    private User currentUser;
    private HashSet<Plant> userPlants;

    @FXML
    private GridPane grid;


    public GardenController() {
        this.switcher = new ViewSwitcher();
    }


    @FXML
    void initialize() {
    }

    @FXML
    void goToHome() throws IOException {
        switcher.switchToScene((Stage) homeBtn.getScene().getWindow(), "/org/example/gardenoftasks/mainPage.fxml", currentUser);
    }

    @FXML
    void goToShop() throws IOException {
        switcher.switchToScene((Stage) shopBtn.getScene().getWindow(), "/org/example/gardenoftasks/shop.fxml", currentUser);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        showUserPlants();
    }

    public void showUserPlants() {
        userPlants = currentUser.getPlants();
        int row = 0;
        int column = 0;
        for (Plant plant : userPlants) {
            try {
                ImageView imageView = new ImageView();
                Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
                imageView.setImage(image);
                AnchorPane pane = new AnchorPane(imageView);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);

                if (column == 5) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
