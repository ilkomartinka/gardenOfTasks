package org.example.gardenoftasks;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Plant;
import model.User;
import util.ViewSwitcher;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
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
        userPlants = new HashSet<>();
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
    }

    public void showUserPlants() {
        int row = 0;
        int column = 0;
        for (Plant plant : currentUser.getPlants()) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
