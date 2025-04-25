module org.example.gardenoftasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.desktop;


    opens org.example.gardenoftasks to javafx.fxml;
    exports org.example.gardenoftasks;
}