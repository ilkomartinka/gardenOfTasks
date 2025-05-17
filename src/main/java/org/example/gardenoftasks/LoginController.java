package org.example.gardenoftasks;

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

    public LoginController() throws IOException, ClassNotFoundException {
        this.um = new UserManager();
        this.slide = new TranslateTransition();
    }

    @FXML
    void initialize() {
        signUpPassword.setVisible(false);
        signUpPassword2.setVisible(false);
        signUpUsername.setVisible(false);
        switchToLoginBtn.setVisible(false);
        b1.setVisible(false);
        signUpButton.setVisible(false);
    }
    @FXML
    void switchToLogin() {
        hideSignUpPage();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(0);
        slide.play();
        showLoginPage();
    }

    @FXML
    void switchToSignUp() {
        hideLoginPage();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(-442);
        slide.play();
        showSignUpPage();
    }


    @FXML
    void login() throws IOException {
        User user = um.login(loginUsername.getText(), loginPassword.getText());
        if (user != null) {
            Stage stage = (Stage) switchToLoginBtn.getScene().getWindow();
            switcher.switchToScene(stage, "/org/example/gardenoftasks/mainPage.fxml",user);
            text.setVisible(true);
            text.setText("Login Successful");
        } else {
            text.setVisible(true);
            text.setText("Invalid Username or Password");
        }
    }


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

    private void hideLoginPage() {
        text1.setVisible(false);
        text.setVisible(false);
        a1.setVisible(false);
        loginButton.setVisible(false);
        loginUsername.setVisible(false);
        loginPassword.setVisible(false);
        switchToSignBtn.setVisible(false);
    }

    private void hideSignUpPage() {
        b1.setVisible(false);
        signUpButton.setVisible(false);
        signUpPassword.setVisible(false);
        signUpPassword2.setVisible(false);
        signUpUsername.setVisible(false);
        switchToLoginBtn.setVisible(false);
    }

    private void showLoginPage() {
        text.setVisible(false);
        switchToSignBtn.setVisible(true);
        loginPassword.setVisible(true);
        loginUsername.setVisible(true);
        loginButton.setVisible(true);
        a1.setVisible(true);
        text1.setVisible(false);
    }

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