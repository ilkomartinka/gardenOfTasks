package manager;

import model.User;

import java.io.*;
import java.util.HashMap;

public  class UserManager {
    private HashMap<String, User> users; //username, User
    private final File file = new File("users.dat");
    private static UserManager instance;

    public UserManager() throws IOException, ClassNotFoundException {
       users =  loadUsers();
    }

    public User login(String username, String password) throws IOException {
        User user = users.get(username);
        if (users.containsKey(username) && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void register(String username, String password) throws IOException, ClassNotFoundException {
        if (!users.containsKey(username)) {
            User user = new User(username, password);
            users.put(username, user);
            save();
        }
    }

    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(users);
        }
    }

    private HashMap<String, User> loadUsers() throws IOException, ClassNotFoundException {
        if (!file.exists() || file.length() == 0) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (HashMap<String, User>) ois.readObject();
            return users;
        }
    }
    public HashMap<String, User> getUsers() {
        return users;
    }

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