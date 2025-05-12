package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private final String username;
    private final String password;
    private int coins;
    private final ArrayList<Task> tasks;  //change
    private final ArrayList<String> flowers; //change

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.coins = 0;
        this.tasks = new ArrayList<>();
        this.flowers = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void addFlower(String flower) {
        flowers.add(flower);
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<String> getFlowers() {
        return flowers;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", coins=" + coins +
                ", tasks=" + tasks +
                ", flowers=" + flowers +
                '}';
    }
}
