package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import org.example.gardenoftasks.GardenController;
import org.example.gardenoftasks.ShopController;
import org.example.gardenoftasks.TaskController;

import java.io.IOException;

public class ViewSwitcher {
    // Switch scene using given FXML file and Stage
    public  void switchToScene(Stage stage, String fxml, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewSwitcher.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        Object controller = fxmlLoader.getController();
        if(controller instanceof TaskController){
            ((TaskController) controller).setCurrentUser(user);
        }else if(controller instanceof ShopController){
            ((ShopController) controller).setCurrentUser(user);
        }else if(controller instanceof GardenController){
            ((GardenController)controller).setCurrentUser(user);
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
}
