module org.example.gardenoftasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.desktop;

    opens org.example.gardenoftasks to javafx.fxml;
    opens model to javafx.fxml;
    opens util to javafx.fxml;
    opens main to javafx.fxml;

    exports org.example.gardenoftasks;
    exports model;
    exports util;
    exports main;
}