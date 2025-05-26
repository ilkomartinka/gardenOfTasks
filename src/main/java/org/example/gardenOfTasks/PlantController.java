package org.example.gardenOfTasks;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Plant;


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

    public void setPlant(Plant plant, ShopController shopController) {
        this.plant = plant;
        nameLabel.setText(plant.getName());
        priceLabel.setText(String.valueOf(plant.getPrice()));
        Image image = new Image(getClass().getResourceAsStream(plant.getImgSrc()));
        imageView.setImage(image);
        plantPane.setOnMouseClicked(event -> {
            shopController.setChosenPlant(plant);
        });
    }

}