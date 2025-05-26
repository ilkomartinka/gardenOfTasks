module org.example.gardenOfTasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.desktop;

    opens org.example.gardenOfTasks to javafx.fxml;
    opens model to javafx.fxml;
    opens util to javafx.fxml;
    opens main to javafx.fxml;

    exports org.example.gardenOfTasks;
    exports model;
    exports util;
    exports main;
}