package org.example.gardenOfTasks;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Plant;

/**
 * Controller for displaying a single plant item in the shop view.
 * Handles the visualization of the plant's image, name, and price,
 * and provides selection behavior when clicked.
 */
public class PlantController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private AnchorPane plantPane;

    private Plant plant;
    /**
     * Initializes and displays the plant's information in the UI.
     * Also sets up click listener to select this plant in the shop controller.
     *
     * @param plant The plant to display.
     * @param shopController The parent shop controller that handles selection.
     */
    public void setPlant(Plant plant, ShopController shopController) {
        this.plant = plant;
        nameLabel.setText(plant.getName());
        priceLabel.setText(String.valueOf(plant.getPrice()));
        Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
        imageView.setImage(image);
        // Allow user to select this plant
        plantPane.setOnMouseClicked(event -> {
            shopController.setChosenPlant(plant);
        });
    }

}