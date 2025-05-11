package org.example.gardenoftasks;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        for (int i = 0; i < 10; i++) {
            plant = new Plant();
            plant.setName("Focusa");
            plant.setPrice(3);
            plant.setImgSrc("/assets/images/plant1.png");
            plants.add(plant);
        }

        return plants;
    }


    @FXML
    void initialize() {
        plants = new ArrayList<>();
        plants.addAll(getPlants());
        int column = 0;
        int row = 1;
        for (int i = 0; i < plants.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("plant.fxml"));
                AnchorPane pane = fxmlLoader.load();
                PlantController plantController = new PlantController();
                plantController.setData(plants.get(i));

                if(column == 3){
                    column = 0;
                    row++;

                }
                grid.add(pane, column++, row);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
