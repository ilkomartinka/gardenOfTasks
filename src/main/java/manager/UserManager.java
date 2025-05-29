package manager;

import model.User;

import java.io.*;
import java.util.HashMap;
/**
 * Manages user registration, login, and saving/loading user data from a file.
 * Uses a singleton pattern to ensure a single instance throughout the application.
 */
public  class UserManager {
    private HashMap<String, User> users; //username, User
    private final File file = new File("users.dat");
    private static UserManager instance;
    /**
     * Loads users from file when the manager is initialized.
     * @throws IOException if the file cannot be read.
     * @throws ClassNotFoundException if the User class cannot be deserialized.
     */
    public UserManager() throws IOException, ClassNotFoundException {
       users =  loadUsers();
    }
    /**
     * Attempts to log in a user.
     * @param username the username.
     * @param password the password.
     * @return the User object if login is successful, or null otherwise.
     */
    public User login(String username, String password) {
        User user = users.get(username);
        if (users.containsKey(username) && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    /**
     * Registers a new user if the username is not already taken.
     * @param username the desired username.
     * @param password the desired password.
     * @throws IOException if saving to file fails.
     */
    public void register(String username, String password) throws IOException {
        if (!users.containsKey(username)) {
            User user = new User(username, password);
            users.put(username, user);
            save();
        }
    }
    /**
     * Saves the user map to the file.
     * @throws IOException if writing to file fails.
     */
    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
        }
    }
    /**
     * Loads the users from the file.
     * @return a HashMap of users.
     * @throws IOException if the file cannot be read.
     * @throws ClassNotFoundException if the User class cannot be deserialized.
     */
    private HashMap<String, User> loadUsers() throws IOException, ClassNotFoundException {
        if (!file.exists() || file.length() == 0) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (HashMap<String, User>) ois.readObject();
            return users;
        }
    }
    /**
     * Returns the map of all users.
     * @return a HashMap of users.
     */
    public HashMap<String, User> getUsers() {
        return users;
    }
    /**
     * Returns the singleton instance of the UserManager.
     * @return the single instance of UserManager.
     * @throws IOException if loading users fails.
     * @throws ClassNotFoundException if deserialization fails.
     */
    public static UserManager getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "UserManager{" +
                "users=" + users +
                '}';
    }
}