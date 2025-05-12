package model;

public class Plant {
    private String name;
    private int price;
    private String imgSrc;

    public Plant(String name, int price, String imgSrc) {
        this.name = name;
        this.price = price;
        this.imgSrc = imgSrc;
    }

    public Plant() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
    public String getImgSrc() {
        return imgSrc;
    }
}
