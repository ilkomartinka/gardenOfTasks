package org.example.gardenoftasks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SwitchController {
    TranslateTransition slide = new TranslateTransition();

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
    private JFXButton switchToLoginBtn;


    @FXML
    void switchToLogin(ActionEvent event) {
        b1.setVisible(false);
        signUpButton.setVisible(false);
        signUpPassword.setVisible(false);
        signUpPassword2.setVisible(false);
        signUpUsername.setVisible(false);
        switchToLoginBtn.setVisible(false);
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(0);
        slide.play();
        switchToSignBtn.setVisible(true);
        loginPassword.setVisible(true);
        loginUsername.setVisible(true);
        loginBUtton.setVisible(true);
        a1.setVisible(true);
    }

    @FXML
    void switchToSignUp(ActionEvent event)  {
        a1.setVisible(false);
        loginBUtton.setVisible(false);
        loginUsername.setVisible(false);
        loginPassword.setVisible(false);
        switchToSignBtn.setVisible(false);
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(sliderPane);
        slide.setToX(-442);
        slide.play();
        b1.setVisible(true);
        signUpButton.setVisible(true);
        signUpPassword.setVisible(true);
        signUpPassword2.setVisible(true);
        signUpUsername.setVisible(true);
        switchToLoginBtn.setVisible(true);

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

}