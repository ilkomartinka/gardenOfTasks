package model;

import javafx.scene.control.Label;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class User implements Serializable {
    private final String username;
    private final String password;
    private int coins;
    private final ArrayList<Task> tasks;  //change
    private final HashSet<Plant> plants; //change

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.coins = 0;
        this.tasks = new ArrayList<>();
        this.plants = new HashSet<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void addPLant(Plant plant) {
        plants.add(plant);
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void removeCoins(int coins) {
        this.coins -= coins;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getCoins() {
        return coins;
    }
    public void updateCoins(Label usersCoins){
        usersCoins.setText(String.valueOf(getCoins()));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public HashSet<Plant> getPlants() {
        return plants;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", coins=" + coins +
                ", tasks=" + tasks +
                ", plants=" + plants +
                '}';
    }
}
