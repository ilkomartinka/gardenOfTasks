package org.example.gardenOfTasks;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Plant;
import model.User;
import util.ViewSwitcher;

import java.io.IOException;
import java.util.HashSet;

/**
 * Controller for the garden scene.
 * Displays the user's purchased plants and allows navigation between scenes.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class GardenController {


    @FXML
    private ToggleButton homeBtn;

    @FXML
    private ToggleButton shopBtn;

    private final ViewSwitcher switcher;
    private User currentUser;

    @FXML
    private GridPane grid;


    /**
     * Constructor initializes the ViewSwitcher.
     */
    public GardenController() {
        this.switcher = new ViewSwitcher();
    }


    @FXML
    void initialize() {
    }

    /**
     * Navigates the user to the Home (main) page.
     */
    @FXML
    void goToHome() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) homeBtn.getScene().getWindow(), "/org/example/gardenOfTasks/mainPage.fxml", currentUser);
    }

    /**
     * Navigates the user to the Shop page.
     */
    @FXML
    void goToShop() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) shopBtn.getScene().getWindow(), "/org/example/gardenOfTasks/shop.fxml", currentUser);
    }

    /**
     * Sets the current user and displays their plants.
     *
     * @param user the currently logged-in user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        showUserPlants();
    }

    /**
     * Displays the user's owned plants in a grid.
     * Each plant is shown as an image.
     */
    public void showUserPlants() {
        HashSet<Plant> userPlants = currentUser.getPlants();
        int row = 0;
        int column = 0;
        for (Plant plant : userPlants) {
            try {
                ImageView imageView = new ImageView();
                Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
                imageView.setImage(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                imageView.setPreserveRatio(true);
                VBox pane = new VBox(imageView);
                pane.setAlignment(Pos.BOTTOM_CENTER);
                if (column == 4) {
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
