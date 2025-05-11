package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewSwitcher {
    // Switch scene using given FXML file and Stage
    public  void switchToScene(Stage stage, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewSwitcher.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
