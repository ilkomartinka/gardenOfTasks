package model;

import java.io.Serializable;
/**
 * Represents a plant that can be bought by the user.
 * Each plant has a name, price, and an image source path.
 */
public class Plant implements Serializable {
    private final String name;
    private final int price;
    private final String imgSrc;
    /**
     * Constructs a new Plant.
     * @param name the name of the plant.
     * @param price the price of the plant in coins.
     * @param imgSrc the path to the image representing the plant.
     */
    public Plant(String name, int price, String imgSrc) {
        this.name = name;
        this.price = price;
        this.imgSrc = imgSrc;
    }
    /**
     * Gets the price of the plant.
     * @return the price in coins.
     */
    public int getPrice() {
        return price;
    }
    /**
     * Gets the name of the plant.
     * @return the plant's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the image path of the plant.
     * @return the image source path.
     */
    public String getImgSrc() {
        return imgSrc;
    }
}
