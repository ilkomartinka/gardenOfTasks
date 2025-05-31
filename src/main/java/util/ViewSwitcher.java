package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import org.example.gardenOfTasks.AddTaskController;
import org.example.gardenOfTasks.GardenController;
import org.example.gardenOfTasks.ShopController;
import org.example.gardenOfTasks.TaskController;

import java.io.IOException;

/**
 * Utility class for switching between different scenes in my JavaFX application.
 */
public class ViewSwitcher {
    /**
     * Switches the current window (stage) to a new scene defined by the specified FXML file.
     * Also passes the logged-in user to the corresponding controller.
     *
     * @param stage the current stage to update
     * @param fxml  the path to the FXML file
     * @param user  the currently logged-in user to pass to the new controller
     * @throws IOException if loading the FXML fails
     */
    public  void switchToScene(Stage stage, String fxml, User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewSwitcher.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        Object controller = fxmlLoader.getController();
        if(controller instanceof TaskController){
            ((TaskController) controller).setCurrentUser(user);
        }else if(controller instanceof ShopController){
            ((ShopController) controller).setCurrentUser(user);
        }else if(controller instanceof GardenController){
            ((GardenController) controller).setCurrentUser(user);
        }else if(controller instanceof AddTaskController){
            ((AddTaskController) controller).setCurrentUser(user);
        }
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
