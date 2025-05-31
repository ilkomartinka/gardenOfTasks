package test;

import manager.TaskManager;
import model.Task;
import model.TaskType;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

    public class TaskManagerTest {
        private TaskManager taskManager;
        private User user;

        @BeforeEach
        void setUp() {
            taskManager = new TaskManager();
            user = new User("test", "1234");
        }

        @Test
        void testAddTask() throws IOException, ClassNotFoundException {
            Task task = new Task("Do homework", TaskType.HOMEWORK,"Math");
            user.addTask(task);
            assertEquals(1, user.getTasks().size());
            assertEquals("Do homework", user.getTasks().getFirst().getDescription());
        }

        @Test
        void testCompleteTask() throws IOException, ClassNotFoundException {
            Task task = new Task("Read book", TaskType.SELF_CARE,"30 min");
            user.addTask(task);
            taskManager.completeTask(task, user);
            assertTrue(task.isDone());
            assertTrue(task.isRewardGiven());
            assertEquals(TaskType.SELF_CARE.getReward(), user.getCoins());
        }

        @Test
        void testUncompleteTask() throws IOException, ClassNotFoundException {
            Task task = new Task("Write test", TaskType.STUDY,"English");
            task.setDone(true);
            user.addTask(task);
            taskManager.uncompleteTask(task);
            assertFalse(task.isDone());
        }

        @Test
        void testCompletedTasksCount() throws IOException, ClassNotFoundException {
            Task task1 = new Task("Task 1", TaskType.WORK_TASK,"Task 1");
            Task task2 = new Task("Task 2", TaskType.EXERCISE,"Task 2");
            task1.setDone(true);
            task2.setDone(false);
            user.addTask(task1);
            user.addTask(task2);
            int count = taskManager.getCompletedTasksCount(user);
            assertEquals(1, count);
        }
    }