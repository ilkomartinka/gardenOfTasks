module org.example.gardenoftasks {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens org.example.gardenoftasks to javafx.fxml;
    exports org.example.gardenoftasks;
}