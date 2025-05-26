package model;

import java.io.Serializable;

public class Plant implements Serializable {
    private final String name;
    private final int price;
    private final String imgSrc;

    public Plant(String name, int price, String imgSrc) {
        this.name = name;
        this.price = price;
        this.imgSrc = imgSrc;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
