package model;
import javafx.scene.control.Label;
import manager.UserManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class User implements Serializable {
    private final String username;
    private final String password;
    private int coins;
    private final ArrayList<Task> tasks;
    private final HashSet<Plant> plants;

    public User(String username, String password) throws IOException, ClassNotFoundException {
        this.username = username;
        this.password = password;
        this.coins = 0;
        this.tasks = new ArrayList<>();
        this.plants = new HashSet<>();
    }

    public void addTask(Task task) throws IOException, ClassNotFoundException {
        tasks.add(task);
        UserManager.getInstance().save();
    }

    public void addPlant(Plant plant) throws IOException, ClassNotFoundException {
        plants.add(plant);
        UserManager.getInstance().save();
    }

    public void addCoins(int coins) throws IOException, ClassNotFoundException {
        this.coins += coins;
        UserManager.getInstance().save();
    }

    public void removeCoins(int coins) throws IOException, ClassNotFoundException {
        this.coins -= coins;
        UserManager.getInstance().save();
    }

    public String getPassword() {
        return password;
    }

    public int getCoins() {
        return coins;
    }

    public void updateCoins(Label usersCoins) {
        usersCoins.setText(String.valueOf(getCoins()));
    }

    public HashSet<Plant> getPlants() {
        return plants;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
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
