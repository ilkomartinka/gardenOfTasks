package org.example.gardenoftasks;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Task;
import manager.TaskManager;
import model.User;
import util.ViewSwitcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TaskController {
    private final ViewSwitcher switcher = new ViewSwitcher();
    private final Stage stage = new Stage();
    private User currentUser;
    private int doneTasksCounter;

    @FXML
    private Button gardenBtn;

    @FXML
    private Button shopBtn;

    @FXML
    private Button toDoBtn;
    @FXML
    private JFXListView<Task> taskList;

    @FXML
    private Text messageText;

    @FXML
    private Label usersCoins;

    @FXML
    private Label quoteLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label doneTasksLabel;

    @FXML
    private Label flowersPlantedLabel;

    @FXML
    private Label activeTimeLabel;

    private ArrayList<String> quotes = new ArrayList<>();


    private final TaskManager taskManager = TaskManager.getInstance();


    @FXML
    void addTaskBtn() throws IOException {
        switcher.switchToScene(stage, "/org/example/gardenoftasks/addTaskPage.fxml", currentUser);
        taskManager.displayTasks(taskList);
    }

    @FXML
    void goToGarden() throws IOException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenoftasks/garden.fxml", currentUser);
    }

    @FXML
    void goToShop() throws IOException {
        switcher.switchToScene((Stage) shopBtn.getScene().getWindow(), "/org/example/gardenoftasks/shop.fxml", currentUser);
    }

    @FXML
    public void initialize() {
        setupTaskListView();
        loadQuotesFromFile();
        startQuoteChanging();
        if (taskList == null) {
            messageText.setVisible(true);
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        user.updateCoins(usersCoins);
        taskManager.displayTasks(taskList);
        flowersPlantedLabel.setText(String.valueOf(currentUser.getPlants().size()));
    }

    private void setupTaskListView() {
        taskList.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setGraphic(null);
                } else {
                    setGraphic(createTask(task));
                    messageText.setVisible(false);
                }
            }
        });
    }

    public Node createTask(Task task) {
        Label taskTypeLabel = createTaskTypeLabel(task);
        Label titleLabel = createTaskLabel(task);
        JFXCheckBox checkBox = createCheckBox(task, titleLabel);
        Label rewardLabel = createRewardLabel(task);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Region spaceBetween = new Region();
        HBox.setHgrow(spaceBetween, Priority.ALWAYS);
        HBox taskLine = new HBox(10, checkBox, titleLabel, spacer, taskTypeLabel, spaceBetween, rewardLabel);
        taskLine.setAlignment(Pos.CENTER_LEFT); // set position in the line
        Tooltip tooltip = new Tooltip(task.getDescription());
        tooltip.setStyle("-fx-font-size: 14px;");
        Tooltip.install(taskLine, tooltip);
        return taskLine;
    }

    private Label createTaskTypeLabel(Task task) {
        Label label = new Label();
        label.setText(task.getTaskType().toString());
        label.setStyle("-fx-font-size: 16px;-fx-font-weight: bold; -fx-text-fill: black;");
        return label;
    }

    private Label createTaskLabel(Task task) {
        Label label = new Label(task.getTaskName());
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000000;");
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
                currentUser.addCoins(task.getTaskType().getReward());
                task.setDone(true);
                doneTasksCounter++;
            } else {
                task.setDone(false);
                doneTasksCounter--;
                currentUser.removeCoins(task.getTaskType().getReward());
            }
            usersCoins.setText(String.valueOf(currentUser.getCoins()));
            doneTasksLabel.setText(String.valueOf(doneTasksCounter));
        });
        return checkBox;
    }

    private Label createRewardLabel(Task task) {
        Label label = new Label();
        label.setText(String.valueOf(task.getTaskType().getReward()));
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #000000; -fx-font-size: 18px;");
        label.setAlignment(Pos.BASELINE_RIGHT);
        return label;
    }

    private void loadQuotesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("quotes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    quotes.add(line.trim());
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void startQuoteChanging() {
        new Thread(() -> {
            try {
                for (int i = 0; i < quotes.size(); i++) {
                    int randomIndex = new Random().nextInt(quotes.size());
                    String quote = quotes.get(randomIndex);

                    Platform.runLater(() -> quoteLabel.setText(quote));
                    quoteLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold");
                    quoteLabel.setAlignment(Pos.CENTER);
                    quoteLabel.setWrapText(true);
                    Thread.sleep(300000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private int totalSeconds = 25 * 60; // 25 minutes in seconds
    private Timeline countdownTimeline;

    @FXML
    void startTimer() {
        startCountdown();
    }

    @FXML
    void stopTimer() {
        countdownTimeline.stop();

        /*String[] parts = timeLabel.getText().split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);

        int totalSeconds = minutes * 60 + seconds;

        int activeTime = totalSeconds - activeSeconds;
        int activeMinutes = activeTime / 60;
        int activeRemainingSeconds = activeTime % 60;

        activeTimeLabel.setText(String.format("%02d:%02d", activeMinutes, activeRemainingSeconds));*/

    }


    private void startCountdown() {
        updateLabel(timeLabel);
        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            totalSeconds--;
            updateLabel(timeLabel);
            if (totalSeconds <= 0) {
                countdownTimeline.stop();
            }
        }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }

    private void updateLabel(Label label) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        label.setText(String.format("%02d:%02d", minutes, seconds));
    }
}