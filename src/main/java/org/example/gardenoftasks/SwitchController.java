package org.example.gardenoftasks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import user.User;
import user.UserManager;

import java.io.IOException;

public class SwitchController {
    private TranslateTransition slide = new TranslateTransition();
    private UserManager um = new UserManager();

    @FXML
    private JFXButton switchToSignBtn;
    @FXML
    private Text a1;

    @FXML
    private Text b1;

    @FXML
    private JFXButton loginBUtton;

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

    public SwitchController() throws IOException, ClassNotFoundException {
    }


    @FXML
    void switchToLogin(ActionEvent event) {
        hideSignUpPage();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(0);
        slide.play();
        showLoginPage();
    }

    @FXML
    void switchToSignUp(ActionEvent event) {
        hideLoginPage();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(-442);
        slide.play();
        showSignUpPage();
    }

    @FXML
    void initialize() {
        switchToLoginBtn.setVisible(false);
        b1.setVisible(false);
        signUpButton.setVisible(false);
        signUpPassword.setVisible(false);
        signUpPassword2.setVisible(false);
        signUpUsername.setVisible(false);
    }

    @FXML
    void login(ActionEvent event) throws IOException, InterruptedException {
        if (um.login(loginUsername.getText(), loginPassword.getText()) != null) {
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();*/
            text.setVisible(true);
            text.setText("Login Successful");
        } else {
            text.setVisible(true);
            text.setText("Invalid Username or Password");
        }
    }


    @FXML
    void register(ActionEvent event) throws IOException {
        if (signUpPassword.getText().equals(signUpPassword2.getText())) {
            um.register(signUpUsername.getText(), signUpPassword.getText());
            text1.setVisible(true);
            text1.setText("Signup successful");
        } else {
            text.setVisible(true);
            text.setText("Passwords do not match");
        }
    }

    private void hideLoginPage() {
        text1.setVisible(false);
        text.setVisible(false);
        a1.setVisible(false);
        loginBUtton.setVisible(false);
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
        loginBUtton.setVisible(true);
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