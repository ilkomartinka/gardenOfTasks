package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * MainApp loads JavaFX application and the first page.
 */
public class MainApp extends Application {
    /**
     * This method is automatically called when the application starts.
     * It sets startPage.fxml as the scene in the main window.
     * @param stage the main stage (window) of the application
     * @throws IOException if the FXML file cannot be loaded
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/gardenOfTasks/startPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 855, 555);
        stage.setTitle("TO DO APP");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The main method that starts the JavaFX application.
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args) {
        launch();
    }
}