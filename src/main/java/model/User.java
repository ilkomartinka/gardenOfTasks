package model;
import javafx.scene.control.Label;
import manager.UserManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Represents a user of the application.
 * A user has a username, password, list of tasks, coins, and collected plants.
 */
public class User implements Serializable {
    private final String username;
    private final String password;
    private int coins;
    private final ArrayList<Task> tasks;
    private final HashSet<Plant> plants;
    /**
     * Creates a new user with the given username and password.
     * @param username the user's name
     * @param password the user's password
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.coins = 0;
        this.tasks = new ArrayList<>();
        this.plants = new HashSet<>();
    }
    /**
     * Adds a task to the user's task list and saves the user data.
     * @param task the task to add
     */
    public void addTask(Task task) throws IOException, ClassNotFoundException {
        tasks.add(task);
        UserManager.getInstance().save();
    }
    /**
     * Adds a plant to the user's collection and saves the user data.
     * @param plant the plant to add
     */
    public void addPlant(Plant plant) throws IOException, ClassNotFoundException {
        plants.add(plant);
        UserManager.getInstance().save();
    }
    /**
     * Adds coins to the user and saves the user.
     * @param coins the number of coins to add
     */
    public void addCoins(int coins) throws IOException, ClassNotFoundException {
        this.coins += coins;
        UserManager.getInstance().save();
    }

    /**
     * Removes coins from the user and saves the user.
     * @param coins the number of coins to remove
     */
    public void removeCoins(int coins) throws IOException, ClassNotFoundException {
        this.coins -= coins;
        UserManager.getInstance().save();
    }

    /**
     * Returns the user's password.
     * @return password as a string
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns the user's current coin balance.
     * @return number of coins
     */
    public int getCoins() {
        return coins;
    }
    /**
     * Updates the coin count in the provided label.
     * @param usersCoins a JavaFX label to show the coin count
     */
    public void updateCoins(Label usersCoins) {
        usersCoins.setText(String.valueOf(getCoins()));
    }

    /**
     * Returns the set of plants owned by the user.
     * @return set of plants
     */
    public HashSet<Plant> getPlants() {
        return plants;
    }
    /**
     * Returns the list of tasks assigned to the user.
     * @return list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
