package org.example.gardenOfTasks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Plant;
import model.User;
import util.ViewSwitcher;

import java.io.*;
import java.util.ArrayList;
/**
 * Controller that handle the shop scene.
 * It loads available plants from a file, displays them in a grid, and
 * manages the purchase process where users can spend coins to buy plants.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class ShopController {

    private final ViewSwitcher switcher;


    @FXML
    private ToggleButton gardenBtn;

    @FXML
    private ToggleButton homeBtn;

    @FXML
    private ImageView plantImage;

    @FXML
    private Label plantNameLabel;

    @FXML
    private Label plantPriceLabel;

    @FXML
    private GridPane grid;

    @FXML
    private Label usersCoins;


    private ArrayList<Plant> plants;

    private User currentUser;

    private Plant chosenPlant;

    @FXML
    private Text congratulationText;

    @FXML
    private Text errorMessage;

    /**
     * Constructor initializing view switcher.
     */
    public ShopController() {
        switcher = new ViewSwitcher();
    }

    /**
     * Switches the view to the garden scene.
     */
    @FXML
    void goToGarden() throws IOException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenOfTasks/garden.fxml", currentUser);
    }

    /**
     * Switches the view to the main (home) scene.
     */
    @FXML
    void goToHome() throws IOException{
        switcher.switchToScene((Stage) homeBtn.getScene().getWindow(), "/org/example/gardenOfTasks/mainPage.fxml", currentUser);
    }

    /**
     * Returns the list of available plants.
     *
     * @return list of plants loaded from file.
     */
    public ArrayList<Plant> getPlants() {
        plants = loadPlants();
        return plants;
    }

    /**
     * Loads plant data from "plants.txt".
     * Each line in the file should follow the format: name,price,imagePath
     *
     * @return list of plant objects
     */
    @SuppressWarnings("CallToPrintStackTrace")
    private ArrayList<Plant> loadPlants() {
        plants = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/files/plants.txt")))) {
                 String line;
            while((line =br.readLine())!=null)

                 {
                     String[] parts = line.split(",");
                     String plantName = parts[0];
                     int price = Integer.parseInt(parts[1]);
                     String image = parts[2];
                     plants.add(new Plant(plantName, price, image));
                 }
             } catch(IOException e) {
            e.printStackTrace();
        }
        return plants;
    }

    /**
     * Displays the selected plant details (image, name, price).
     *
     * @param plant the selected plant
     */
    public void setChosenPlant(Plant plant) {
        this.chosenPlant = plant;
        plantNameLabel.setText(plant.getName());
        plantPriceLabel.setText(String.valueOf(plant.getPrice()));
        Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
        plantImage.setImage(image);
    }

    /**
     * Returns the currently selected plant.
     *
     * @return selected plant
     */
    public Plant getChosenPlant() {
        return chosenPlant;
    }

    /**
     * Initializes the shop UI: loads plants and sets grid.
     */
    @FXML
    void initialize() {
        plants = getPlants();
        int column = 0;
        int row = 1;
        congratulationText.setVisible(false);
        errorMessage.setVisible(false);
        for (Plant value : plants) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/gardenOfTasks/plant.fxml"));
                AnchorPane pane = fxmlLoader.load();
                PlantController plantController = fxmlLoader.getController();
                plantController.setPlant(value, this);
                grid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);
    }

    /**
     * Manages the process of buying the selected plant.
     * If the user has enough coins, the plant is added to their collection.
     * Updates UI and shows feedback messages.
     */
    @FXML
    void buyPlant() throws IOException, ClassNotFoundException {
        if (currentUser.getCoins() >= chosenPlant.getPrice()) {
            currentUser.removeCoins(getChosenPlant().getPrice());
            usersCoins.setText(String.valueOf(currentUser.getCoins()));
            currentUser.addPlant(chosenPlant);
            errorMessage.setVisible(false);
            congratulationText.setVisible(true);
            currentUser.updateCoins(usersCoins);
        } else {
            congratulationText.setVisible(false);
            errorMessage.setVisible(true);
        }
    }

    /**
     * Sets the current user and updates the coins label in the UI.
     *
     * @param user the current logged-in user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        currentUser.updateCoins(usersCoins);
    }
}