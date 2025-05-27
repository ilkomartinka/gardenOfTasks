package org.example.gardenOfTasks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("CallToPrintStackTrace")
public class ShopController {

    private final ViewSwitcher switcher;


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
    private Label usersCoins;


    private ArrayList<Plant> plants;

    private User currentUser;

    private Plant chosenPlant;

    @FXML
    private Text congratulationText;

    @FXML
    private Text errorMessage;

    public ShopController() {
        switcher = new ViewSwitcher();
    }

    @FXML
    void goToGarden() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenOfTasks/garden.fxml", currentUser);
    }

    @FXML
    void goToHome() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) homeBtn.getScene().getWindow(), "/org/example/gardenOfTasks/mainPage.fxml", currentUser);
    }

    public ArrayList<Plant> getPlants() {
        plants = loadPlants();
        return plants;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private ArrayList<Plant> loadPlants() {
        plants = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("plants.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String plantName = parts[0];
                int price = Integer.parseInt(parts[1]);
                String image = parts[2];
                plants.add(new Plant(plantName, price, image));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plants;
    }

    public void setChosenPlant(Plant plant) {
        this.chosenPlant = plant;
        plantNameLabel.setText(plant.getName());
        plantPriceLabel.setText(String.valueOf(plant.getPrice()));
        Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
        plantImage.setImage(image);
    }

    public Plant getChosenPlant() {
        return chosenPlant;
    }

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

    @FXML
    void buyPlant() throws IOException, ClassNotFoundException {
        if(currentUser.getCoins() >= chosenPlant.getPrice()) {
            currentUser.removeCoins(getChosenPlant().getPrice());
            usersCoins.setText(String.valueOf(currentUser.getCoins()));
            currentUser.addPlant(chosenPlant);
            errorMessage.setVisible(false);
            congratulationText.setVisible(true);
            currentUser.updateCoins(usersCoins);
        }else{
            congratulationText.setVisible(false);
            errorMessage.setVisible(true);
        }

    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        currentUser.updateCoins(usersCoins);
    }

}