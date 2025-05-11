package org.example.gardenoftasks;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Plant;



public class PlantController {

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;


    private Plant plant;


    public void setData(Plant plant) {
        this.plant = plant;
        nameLabel.setText(plant.getName());
        priceLabel.setText(String.valueOf(plant.getPrice()));
        Image image = new Image(getClass().getResourceAsStream("/assets/plant1.png"));
        imageView.setImage(image);
    }

}