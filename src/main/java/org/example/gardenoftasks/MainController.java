package org.example.gardenoftasks;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Task;
import manager.TaskManager;
import model.User;
import util.ViewSwitcher;

import java.io.IOException;

public class MainController {
    private final ViewSwitcher switcher = new ViewSwitcher();
    private final Stage stage = new Stage();
    private User currentUser;

    @FXML
    private Button gardenBtn;

    @FXML
    private Button shopBtn;

    @FXML
    private Button toDoBtn;
    @FXML
    private JFXListView<Task> taskList;
    @FXML
    private Text massageText;

    private final TaskManager taskManager = TaskManager.getInstance();

    @FXML
    void addTaskBtn() throws IOException {
        switcher.switchToScene(stage, "addTaskPage.fxml");
        taskManager.displayTasks(taskList);
    }

    @FXML
    void goToGarden() throws IOException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenoftasks/garden.fxml");
    }

    @FXML
    void goToShop() throws IOException {
        switcher.switchToScene((Stage) shopBtn.getScene().getWindow(), "/org/example/gardenoftasks/shop.fxml");
    }

    @FXML
    public void initialize() {
        setupTaskListView();
    }


    private void setupTaskListView() {
        taskList.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    massageText.setVisible(true);
                    setGraphic(null);
                } else {
                    setGraphic(createTask(task));
                    massageText.setVisible(false);
                }
            }
        });
    }

    private Node createTask(Task task) {
        Label taskTypeLabel = createTaskTypeLabel(task);
        Label titleLabel = createTaskLabel(task);
        JFXCheckBox checkBox = createCheckBox(task, titleLabel);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox taskLine = new HBox(10, checkBox, titleLabel, spacer, taskTypeLabel);
        taskLine.setAlignment(Pos.CENTER_LEFT); // set position in the line
        return taskLine;
    }

    private Label createTaskTypeLabel(Task task) {
        Label label = new Label(task.getTaskType().toString());
        label.setStyle("-fx-font-size: 16px;-fx-font-weight: bold; -fx-text-fill: black;");
        return label;
    }

    private Label createTaskLabel(Task task) {
        Label label = new Label(task.getTaskName());
        label.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        label.setWrapText(true); // allows text to go to a new line
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMaxWidth(Region.USE_COMPUTED_SIZE);
        return label;
    }

    private JFXCheckBox createCheckBox(Task task, Label titleLabel) {
        JFXCheckBox checkBox = new JFXCheckBox();
        checkBox.setAlignment(Pos.CENTER_LEFT);
        checkBox.setOnAction(event -> {
            if (checkBox.isSelected()) {
                titleLabel.setStyle("-fx-strikethrough: true");
                task.setDone(true);
            }

        });
        return checkBox;
    }


}