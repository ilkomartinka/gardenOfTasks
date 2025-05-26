package manager;

import model.User;

import java.io.*;
import java.util.HashMap;

public class UserManager implements Serializable {
    private HashMap<String, User> users; //username, User
    private final File file = new File("users.dat");

    public UserManager() throws IOException, ClassNotFoundException {
        users = loadUsers();
    }

    public User login(String username, String password) throws IOException {
        User user = users.get(username);
        if (users.containsKey(username) && user.getPassword().equals(password)) {
            saveUsers();
            return user;
        }
        return null;
    }

    public void register(String username, String password) throws IOException {
        if (!users.containsKey(username)) {
            User user = new User(username, password);
            users.put(username, user);
            saveUsers();
        }
    }

    private void saveUsers() throws IOException {
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

    @Override
    public String toString() {
        return "UserManager{" +
                "users=" + users +
                '}';
    }
}