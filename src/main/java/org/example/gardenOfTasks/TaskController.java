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

/**
 * Controller class for the mainPage scene.
 * Handles task display, user interactions such as completing or clearing tasks,
 * navigation to other scenes (shop, garden, task creation), updating UI components
 * (like progress bar, labels, timer), and displaying motivational quotes.
 */
@SuppressWarnings("CallToPrintStackTrace")
public class TaskController {
    private final ViewSwitcher switcher = new ViewSwitcher();
    private final Stage stage = new Stage();
    private User currentUser;

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

    /**
     * Opens the Add Task page and refreshes the task list and user data after the window is closed.
     */
    @FXML
    void addTaskBtn() throws IOException, ClassNotFoundException {
        switcher.switchToScene(stage, "/org/example/gardenOfTasks/addTaskPage.fxml", currentUser);
        stage.setOnHiding(event -> {
            try {
                taskManager.displayTasks(currentUser, taskList);
                updateProgressBar();
                doneTasksLabel.setText(String.valueOf(taskManager.getCompletedTasksCount(currentUser)));
                usersCoins.setText(String.valueOf(currentUser.getCoins()));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Navigates to the garden scene.
     */
    @FXML
    void goToGarden() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) gardenBtn.getScene().getWindow(), "/org/example/gardenOfTasks/garden.fxml", currentUser);
    }

    /**
     * Navigates to the shop scene.
     */
    @FXML
    void goToShop() throws IOException, ClassNotFoundException {
        switcher.switchToScene((Stage) shopBtn.getScene().getWindow(), "/org/example/gardenOfTasks/shop.fxml", currentUser);
    }

    /**
     * Clears all tasks for the current user and updates UI.
     */
    @FXML
    void clearTasks() throws IOException, ClassNotFoundException {
        taskManager.clearTasks(currentUser);
        taskList.getItems().clear();
        progressBar.setProgress(0);
        progressLabel.setText("0%");
        doneTasksLabel.setText("0");
        UserManager.getInstance().save();
    }

    /**
     * Initializes the controller.
     * Loads quotes, starts timer and displays the user's tasks in the list view.
     */
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        setupTaskListView();
        startQuoteChanging();
        updateTimeLabel();
        if (taskList == null) {
            messageText.setVisible(true);
        }
    }

    /**
     * Sets the current user and initializes UI based on user data.
     *
     * @param user the current logged-in user
     */
    public void setCurrentUser(User user) throws IOException, ClassNotFoundException {
        this.currentUser = user;
        setupTaskListView();
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

    /**
     * Sets up the visual representation of tasks using a custom ListCell.
     */
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

    /**
     * Creates a visual HBox representation of a single task.
     *
     * @param task the task to be displayed
     * @return Node representing the task
     */
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
    /**
     * Creates a label that displays the task's type.
     *
     * @param task the task whose type will be displayed
     * @return a styled Label representing the task type
     */
    private Label createTaskTypeLabel(Task task) {
        Label label = new Label();
        label.setText(task.getTaskType().toString());
        label.setStyle("-fx-font-size: 16px;-fx-font-weight: bold; -fx-text-fill: black;");
        return label;
    }
    /**
     * Creates a label that displays the task's name.
     * The label is styled, left-aligned, and wraps text if it is too long.
     *
     * @param task the task whose name will be displayed
     * @return a styled Label with the task name
     */
    private Label createTaskLabel(Task task) {
        Label label = new Label(task.getTaskName());
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        label.setWrapText(true); // allows text to go to a new line
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMaxWidth(Region.USE_COMPUTED_SIZE);
        return label;
    }
    /**
     * Creates a checkbox for marking a task as completed or not.
     * Handles updating the task status, user's coins, done tasks count,
     * and progress bar when the checkbox is toggled.
     *
     * @param task the task associated with the checkbox
     * @return a  JFXCheckBox that reflects and updates the task's done status
     */
    private JFXCheckBox createCheckBox(Task task) {
        JFXCheckBox checkBox = new JFXCheckBox();
        checkBox.setAlignment(Pos.CENTER_LEFT);
        checkBox.setSelected(task.isDone());
        checkBox.setOnAction(event -> {
            try {
                if (checkBox.isSelected() && !task.isDone()) {
                    taskManager.completeTask(task, currentUser);
                } else if (!checkBox.isSelected() && task.isDone()) {
                    taskManager.uncompleteTask(task);
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
    /**
     * Creates a label that displays the reward value of a task.
     *
     * @param task the task whose reward will be shown
     * @return a styled Label with the reward amount
     */
    private Label createRewardLabel(Task task) {
        Label label = new Label();
        label.setText(String.valueOf(task.getTaskType().getReward()));
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #000000; -fx-font-size: 18px;");
        label.setAlignment(Pos.BASELINE_RIGHT);
        return label;
    }

    /**
     * Loads motivational quotes from a text file.
     */
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

    /**
     * Starts a background thread that changes the motivational quote every 3 minutes.
     */
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

    /**
     * Starts the countdown timer (25 minutes).
     */
    @FXML
    void startTimer() {
        startCountdown();
    }

    /**
     * Stops the active countdown timer.
     */
    @FXML
    void stopTimer() {
        countdownTimeline.stop();
    }

    /**
     * Starts and manages countdown logic, updates the timer label every second.
     */
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

    /**
     * Updates the countdown label in MM:SS format.
     */
    private void updateTimeLabel() {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Updates the progress bar and label based on the number of completed tasks.
     */
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