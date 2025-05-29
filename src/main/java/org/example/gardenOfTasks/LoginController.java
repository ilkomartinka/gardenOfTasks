package org.example.gardenOfTasks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import manager.UserManager;
import model.User;
import util.ViewSwitcher;

import java.io.IOException;
/**
 * Controller class for handling login and registration functionality.
 * Includes animated transitions between login and signup panels.
 */
public class LoginController {
    private final TranslateTransition slide;
    private final UserManager um;

    @FXML
    private JFXButton switchToSignBtn;

    @FXML
    private Text a1;

    @FXML
    private Text b1;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField loginPassword;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField signUpPassword;

    @FXML
    private JFXTextField signUpPassword2;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private AnchorPane sliderPane;

    @FXML
    private Text text;

    @FXML
    private Text text1;

    @FXML
    private JFXButton switchToLoginBtn;

    private final ViewSwitcher switcher = new ViewSwitcher();
    /**
     * Constructor initializes UserManager and transition animation.
     */

    public LoginController() throws IOException, ClassNotFoundException {
        this.um = UserManager.getInstance();
        this.slide = new TranslateTransition();
    }
    /**
     * Initializes the UI, only the login page visible.
     */
    @FXML
    void initialize() {
        signUpPassword.setVisible(false);
        signUpPassword2.setVisible(false);
        signUpUsername.setVisible(false);
        switchToLoginBtn.setVisible(false);
        b1.setVisible(false);
        signUpButton.setVisible(false);
    }
    /**
     * Switches from signup panel back to log in panel with animation.
     */
    @FXML
    void switchToLogin() {
        hideSignUpPage();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(0);
        slide.play();
        showLoginPage();
    }
    /**
     * Switches from login panel to signup panel with animation.
     */
    @FXML
    void switchToSignUp() {
        hideLoginPage();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(-442);
        slide.play();
        showSignUpPage();
    }
    /**
     * Handles login logic. If user exist, switches to main page.
     */
    @FXML
    void login() throws IOException, ClassNotFoundException {
        User user = um.login(loginUsername.getText(), loginPassword.getText());
        if (user != null) {
            Stage stage = (Stage) switchToLoginBtn.getScene().getWindow();
            switcher.switchToScene(stage, "/org/example/gardenOfTasks/mainPage.fxml",user);
        } else {
            text.setVisible(true);
            text.setText("Invalid Username or Password");
        }
    }
    /**
     * Handles user registration. Checks if passwords match and user does not already exist.
     */
    @FXML
    void register() throws IOException {
        text.setVisible(true);
        if (signUpPassword.getText().equals(signUpPassword2.getText())) {
            if (!um.getUsers().containsKey(signUpUsername.getText())) {
                um.register(signUpUsername.getText(), signUpPassword.getText());
                text.setText("Signup successful, now you can login");
            } else {
                text.setText("User already exists");
            }
        } else {
            text.setText("Passwords do not match");
        }
    }
    /**
     * Hides UI elements related to log in view.
     */
    private void hideLoginPage() {
        text1.setVisible(false);
        text.setVisible(false);
        a1.setVisible(false);
        loginButton.setVisible(false);
        loginUsername.setVisible(false);
        loginPassword.setVisible(false);
        switchToSignBtn.setVisible(false);
    }
    /**
     * Hides UI elements related to signup view.
     */
    private void hideSignUpPage() {
        b1.setVisible(false);
        signUpButton.setVisible(false);
        signUpPassword.setVisible(false);
        signUpPassword2.setVisible(false);
        signUpUsername.setVisible(false);
        switchToLoginBtn.setVisible(false);
    }
    /**
     * Shows login panel components.
     */
    private void showLoginPage() {
        text.setVisible(false);
        switchToSignBtn.setVisible(true);
        loginPassword.setVisible(true);
        loginUsername.setVisible(true);
        loginButton.setVisible(true);
        a1.setVisible(true);
        text1.setVisible(false);
    }
    /**
     * Shows signup panel components.
     */
    private void showSignUpPage() {
        b1.setVisible(true);
        signUpButton.setVisible(true);
        signUpPassword.setVisible(true);
        signUpPassword2.setVisible(true);
        signUpUsername.setVisible(true);
        switchToLoginBtn.setVisible(true);
        text.setVisible(false);
    }
}