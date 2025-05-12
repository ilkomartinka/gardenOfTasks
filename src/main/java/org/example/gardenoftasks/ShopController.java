package org.example.gardenoftasks;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Plant;
import util.ViewSwitcher;

import java.io.IOException;
import java.util.ArrayList;

public class ShopController {

    private ViewSwitcher switcher;

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

    @FXML
    private Label usersCoins;

    private Plant plant;

    private ArrayList<Plant> plants;


    public ShopController() {
        switcher = new ViewSwitcher();
    }

    @FXML
    void goToGarden() throws IOException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenoftasks/garden.fxml");
    }

    @FXML
    void goToHome() throws IOException {
        switcher.switchToScene((Stage) homeBtn.getScene().getWindow(), "/org/example/gardenoftasks/mainPage.fxml");
    }

    public ArrayList<Plant> getPlants() {
        plants = new ArrayList<>();

        plant = new Plant();
        plant.setName("Focusa");
        plant.setPrice(3);
        plant.setImgSrc("/assets/plant1.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Lilly");
        plant.setPrice(15);
        plant.setImgSrc("/assets/plant2.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Bloomy");
        plant.setPrice(10);
        plant.setImgSrc("/assets/plant3.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Alurea");
        plant.setPrice(5);
        plant.setImgSrc("/assets/plant4.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Velina");
        plant.setPrice(5);
        plant.setImgSrc("/assets/plant4.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Lilure");
        plant.setPrice(5);
        plant.setImgSrc("/assets/plant5.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Zaflora");
        plant.setPrice(5);
        plant.setImgSrc("/assets/plant6.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Bloomera");
        plant.setPrice(15);
        plant.setImgSrc("/assets/plant7.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Brillora");
        plant.setPrice(10);
        plant.setImgSrc("/assets/plant8.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Sunelle");
        plant.setPrice(10);
        plant.setImgSrc("/assets/plant9.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Jollia");
        plant.setPrice(8);
        plant.setImgSrc("/assets/plant10.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Viveli");
        plant.setPrice(5);
        plant.setImgSrc("/assets/plant11.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Serafina");
        plant.setPrice(8);
        plant.setImgSrc("/assets/plant12.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Elorine");
        plant.setPrice(11);
        plant.setImgSrc("/assets/plant13.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Camirelle");
        plant.setPrice(15);
        plant.setImgSrc("/assets/plant14.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Bloomy");
        plant.setPrice(13);
        plant.setImgSrc("/assets/plant15.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Rosmara");
        plant.setPrice(18);
        plant.setImgSrc("/assets/plant16.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Thistelle");
        plant.setPrice(15);
        plant.setImgSrc("/assets/plant17.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Ariosa");
        plant.setPrice(20);
        plant.setImgSrc("/assets/plant18.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Zephyra");
        plant.setPrice(25);
        plant.setImgSrc("/assets/plant19.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Sunbloom");
        plant.setPrice(25);
        plant.setImgSrc("/assets/plant20.png");
        plants.add(plant);

        plant = new Plant();
        plant.setName("Velina");
        plant.setPrice(30);
        plant.setImgSrc("/assets/plant21.png");
        plants.add(plant);

        return plants;
    }

    public void setChosenPlant(Plant plant) {
        plantNameLabel.setText(plant.getName());
        plantPriceLabel.setText(String.valueOf(plant.getPrice()));
        Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
        plantImage.setImage(image);
    }


    @FXML
    void initialize() {
        plants = getPlants();
        int column = 0;
        int row = 1;
        for (int i = 0; i < plants.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/gardenoftasks/plant.fxml"));
                AnchorPane pane = fxmlLoader.load();
                PlantController plantController = fxmlLoader.getController();
                plantController.setData(plants.get(i), this);
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
}