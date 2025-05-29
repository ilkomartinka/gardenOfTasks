package test;

import manager.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import model.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {

        private UserManager userManager;

        @BeforeEach
        void setUp() throws IOException, ClassNotFoundException {
            userManager = new UserManager();
        }

        @Test
        void testRegisterAndLoginSuccess() throws IOException, ClassNotFoundException {
            String username = "testUser";
            String password = "testPass";

            userManager.register(username, password);
            User loggedInUser = userManager.login(username, password);

            assertNotNull(loggedInUser);
            assertEquals(username, loggedInUser.toString().contains(username) ? username : null);
        }

        @Test
        void testLoginWithWrongPassword() throws IOException {
            String username = "testUser";
            String wrongPassword = "wrongPass";

            User result = userManager.login(username, wrongPassword);
            assertNull(result);
        }
    }

