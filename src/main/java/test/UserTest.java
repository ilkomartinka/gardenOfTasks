package test;

import model.Plant;
import model.TaskType;
import model.User;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("CallToPrintStackTrace")
public class UserTest {
    private User user;
    private Plant plant;

    @BeforeEach
    public void setUp() {
            user = new User("testUser", "password");
            plant = new Plant("Test plant", 25, "Test image");
    }

    @Test
    void testAddTask() throws IOException, ClassNotFoundException {

        model.Task task = new model.Task("Test task", TaskType.HOMEWORK, "Test description");

        user.addTask(task);
        ArrayList<Task> tasks = user.getTasks();

        assertEquals(1, tasks.size(), "Task list should contain 1 task");
        assertEquals(task, tasks.getFirst(), "Task added should be the one created");
    }

    @Test
    void testAddPlant() throws IOException, ClassNotFoundException {
        user.addPlant(plant);
        assertTrue(user.getPlants().contains(plant));
    }

    @Test
    public void testAddCoins() throws IOException, ClassNotFoundException {
        user.addCoins(10);
        assertEquals(10, user.getCoins());
    }

    @Test
    public void testRemoveCoins() throws IOException, ClassNotFoundException {
        user.addCoins(20);
        user.removeCoins(5);
        assertEquals(15, user.getCoins());
    }
}
