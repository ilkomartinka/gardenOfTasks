package org.example.gardenOfTasks;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.animation.KeyFrame;
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
import manager.UserManager;
import model.Task;
import manager.TaskManager;
import model.User;
import util.ViewSwitcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("CallToPrintStackTrace")
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
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;


    private final ArrayList<String> quotes;


    private final TaskManager taskManager;

    public TaskController() {
        taskManager = TaskManager.getInstance();
        quotes = new ArrayList<>();
    }

    @FXML
    void addTaskBtn() throws IOException, ClassNotFoundException {
        switcher.switchToScene(stage, "/org/example/gardenOfTasks/addTaskPage.fxml", currentUser);
        taskManager.displayTasks(currentUser,taskList);
    }

    @FXML
    void goToGarden() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenOfTasks/garden.fxml", currentUser);
    }

    @FXML
    void goToShop() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) shopBtn.getScene().getWindow(), "/org/example/gardenOfTasks/shop.fxml", currentUser);
    }

    @FXML
    void clearTasks() throws IOException, ClassNotFoundException {
        taskManager.clearTasks(currentUser);
        taskList.getItems().clear();
        progressBar.setProgress(0);
        doneTasksLabel.setText("0");
        UserManager.getInstance().save();
    }

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        setupTaskListView();
        startQuoteChanging();
        updateTimeLabel();
        if (taskList == null) {
            messageText.setVisible(true);
        }
    }

    public void setCurrentUser(User user) throws IOException, ClassNotFoundException {
        this.currentUser = user;
        user.updateCoins(usersCoins);
        taskManager.displayTasks(currentUser, taskList);
        flowersPlantedLabel.setText(String.valueOf(currentUser.getPlants().size()));
        doneTasksLabel.setText(String.valueOf(taskManager.getCompletedTasksCount(currentUser)));
        try {
            updateProgressBar();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setupTaskListView() throws IOException, ClassNotFoundException {
        taskList.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setGraphic(null);
                } else {
                    try {
                        setGraphic(createTask(task));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    messageText.setVisible(false);
                }
            }
        });
        UserManager.getInstance().save();
    }

    public Node createTask(Task task) throws IOException {
        Label taskTypeLabel = createTaskTypeLabel(task);
        Label titleLabel = createTaskLabel(task);
        JFXCheckBox checkBox = createCheckBox(task);
        Label rewardLabel = createRewardLabel(task);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Region spaceBetween = new Region();
        HBox.setHgrow(spaceBetween, Priority.ALWAYS);
        HBox taskLine = new HBox(10, checkBox, titleLabel, spacer, taskTypeLabel, spaceBetween, rewardLabel);
        taskLine.setAlignment(Pos.CENTER_LEFT);
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

    private JFXCheckBox createCheckBox(Task task) {
        JFXCheckBox checkBox = new JFXCheckBox();
        checkBox.setAlignment(Pos.CENTER_LEFT);
        checkBox.setSelected(task.isDone());
        checkBox.setOnAction(event -> {
            try {
                if (checkBox.isSelected() && !task.isDone()) {
                    taskManager.completeTask(task, currentUser);
                    doneTasksCounter++;
                } else if (!checkBox.isSelected() && task.isDone()) {
                    taskManager.uncompleteTask(task, currentUser);
                    doneTasksCounter--;
                }
                usersCoins.setText(String.valueOf(currentUser.getCoins()));
                doneTasksLabel.setText(String.valueOf(taskManager.getCompletedTasksCount(currentUser)));
                updateProgressBar();
                UserManager.getInstance().save();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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
                if (!line.isEmpty()) {
                    quotes.add(line.trim());
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void startQuoteChanging() {
        loadQuotesFromFile();
        new Thread(() -> {
            try {
                for (int i = 0; i < quotes.size(); i++) {
                    int randomIndex = new Random().nextInt(quotes.size());
                    String quote = quotes.get(randomIndex);
                    Platform.runLater(() -> quoteLabel.setText(quote));
                    quoteLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold");
                    quoteLabel.setAlignment(Pos.CENTER);
                    quoteLabel.setWrapText(true);
                    Thread.sleep(180000);
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
    }

    private void startCountdown() {
        updateTimeLabel();
        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            totalSeconds--;
            updateTimeLabel();
            if (totalSeconds <= 0) {
                countdownTimeline.stop();
            }
        }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }

    private void updateTimeLabel() {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateProgressBar() throws IOException, ClassNotFoundException {
        int totalTasks = currentUser.getTasks().size();
        int doneTasks = taskManager.getCompletedTasksCount(currentUser);
        if (totalTasks != 0) {
            double progress = (double) doneTasks / totalTasks;
            progressBar.setProgress(progress);
            int percent = (int) (progress * 100);
            progressLabel.setText(percent + "%");
        }
        UserManager.getInstance().save();
    }
}