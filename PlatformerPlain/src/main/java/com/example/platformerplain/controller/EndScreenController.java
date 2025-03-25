package com.example.platformerplain.controller;

import com.example.platformerplain.model.LevelData;
import com.example.platformerplain.util.GameInitializer;
import com.example.platformerplain.util.HighScoreUtils;
import com.example.platformerplain.view.MainScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controls the end screen of the game.
 * <p>
 *     Extends the 'ScreenBaseController' and manages the end screen's interactions and behaviour.
 *     <br><br>
 *     This class handles the transition to the end screen,
 *     displays current score and highest scores.
 * </p>
 */
public class EndScreenController extends ScreenBaseController {

    /**
     * A Text node used to display the "Game Over" message.
     */
    @FXML
    private Text gameOverText;

    /**
     * A Text node used to display the player's score.
     */
    @FXML
    private Text scoreText;

    /**
     * A Text node used to display the highest score.
     */
    @FXML
    private Text highestScoreText;

    /**
     * A VBox container used to display the high scores list.
     */
    @FXML
    private VBox highScoresVBox;

    /**
     * A ScrollPane container used to display the high scores list.
     */
    @FXML
    ScrollPane highScoresScrollPane;

    /**
     * A Button that allows the player to start a new game.
     */
    @FXML
    private Button playAgainBtn;

    /**
     * A Button that allows the player to exit the game.
     */
    @FXML
    Button exitGameBtn;

    /**
     * A Button that navigates the player back to the main menu.
     */
    @FXML
    Button backToMenuBtn;

    /**
     * A Button that allows the player to proceed to the next level.
     */
    @FXML
    private Button nextLevelBtn;

    /**
     * A Button that toggles the display of the high scores list.
     */
    @FXML
    private Button toggleHighScoresBtn;

    /**
     * The primary stage of the application.
     * <p>
     *     Controls and displays the primary user interface elements.
     * </p>
     */
    private Stage primaryStage;

    /**
     * The final score achieved by the player.
     */
    private int finalScore;

    /**
     * Data related to the levels in the game.
     * <p>
     *     Contains an array of strings, each representing the title of level.
     * </p>
     */
    String[] levelData;

    /**
     * Indicates whether the level has been completed.
     */
    private boolean levelCompleted;

    /**
     * The title of the current level.
     */
    private String levelName;

    /**
     * Indicates whether the high scores have been viewed.
     * <p>
     *     The boolean flag is initialized to false by default,
     *     indicating that by default, the high scores have not been viewed.
     * </p>
     */
    private boolean highScoresViewed = false;


    /**
     * The game initializer responsible for setting up the game environment.
     */
    private GameInitializer gameInitializer;


    /**
     * Indicates whether the game is running in test mode.
     */
    private boolean isTestMode = false;

    /**
     * Initialises the end screen controller.
     * <p>
     *     The initialise method is called to initialise the end screen controller. It calls the
     *     {@code initialize} method of the class {@code ScreenBaseController} to ensure that any
     *     necessary setup defined in the class is also executed.
     * </p>
     */
    @FXML
    public void initialize() {
        super.initialize();  // Call the base class initialize method
    }

    /**
     * Sets the primary stage for the game.
     *
     * @param primaryStage the primary stage to set
     */
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Returns the primary stage of the application.
     *
     * @return The primary stage of the application
     */
    public Stage getStage() {
        return primaryStage;
    }

    /**
     * Sets the game initializer responsible for setting up the game environment.
     *
     * @param gameInitializer the game initializer to set
     */
    public void setGameInitializer(GameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
    }

    /**
     * Returns the game initializer responsible for setting up the game environment.
     *
     * @return The game initializer
     */
    public GameInitializer getGameInitializer() {
        return gameInitializer;
    }

    /**
     * Sets the final score and updates the display and high scores if necessary.
     *
     * @param finalScore the final score to set
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
        displayFinalScore(finalScore);
        if (levelName != null) {
            updateHighScore(levelName, finalScore);
        } else {
            System.err.println("Error: levelName is null when setting final score");
        }
    }

    /**
     * Displays the final score on the end screen.
     *
     * @param finalScore the final score to display
     */
    private void displayFinalScore(int finalScore) {
        scoreText.setText(String.format("Final Score: %07d", finalScore));
    }

    /**
     * Updates the high score for the given level and displays
     * if the high scores haven't been viewed.
     *
     * @param levelName the name of the level
     * @param finalScore the final score to set as the high score
     */
    private void updateHighScore(String levelName, int finalScore) {
        System.out.println("Updating high score for level: " + levelName);
        HighScoreUtils.writeHighScore(levelName, finalScore);
        if (!highScoresViewed) {
            displayHighestScore(levelName);
        }
    }

    /**
     * Sets the level data and determines the level name.
     *
     * @param levelData the data of the level
     */
    public void setLevelData(String[] levelData) {
        this.levelData = levelData;
        this.levelName = getLevelName(levelData);
        System.out.println("Level name set to: " + levelName);
    }

    /**
     * Sets the level completion status and updates the "Next Level" button visibility.
     *
     * @param levelCompleted true if the level is completed, false if it is not
     */
    public void setLevelCompleted(boolean levelCompleted) {
        this.levelCompleted = levelCompleted;
        updateGameOverText();
        nextLevelBtn.setVisible(levelCompleted && !isFinalLevel());  // Show next level button only if level completed and not the final level
    }

    /**
     * Updates the "Game Over" text depending on whether the level is completed or not.
     */
    private void updateGameOverText() {
        if (levelCompleted) {
            gameOverText.setText(levelName + " Complete!");
        } else {
            gameOverText.setText("Game Over!");
        }
    }

    /**
     * Reads and displays the highest score for the given level.
     *
     * @param levelName the name of the level
     */
    private void displayHighestScore(String levelName) {
        List<Integer> highScores = HighScoreUtils.readHighScores(levelName);
        showHighestScoreText();
        hideHighScoresScrollPane();
        setHighestScoreText(levelName, highScores);
    }

    /**
     * Makes the highest score text visible to the player.
     */
    private void showHighestScoreText() {
        highestScoreText.setVisible(true);
    }

    /**
     * Hides the high scores scroll pane from the player.
     */
    private void hideHighScoresScrollPane() {
        highScoresScrollPane.setVisible(false);
    }

    /**
     * Sets the text for the highest score.
     *
     * @param levelName the name of the level
     * @param highScores the list of high scores
     */
    private void setHighestScoreText(String levelName, List<Integer> highScores) {
        if (!highScores.isEmpty()) {
            int highestScore = highScores.get(0);
            highestScoreText.setText(levelName + " Highest Score: " + highestScore);
        } else {
            highestScoreText.setText(levelName + " No High Scores Yet");
        }
    }

    /**
     * Displays the high scores list for the given level.
     *
     * @param levelName the name of the level
     */
    private void displayHighScores(String levelName) {
        List<Integer> highScores = HighScoreUtils.readHighScores(levelName);
        logHighScores(levelName, highScores);
        clearHighScoresVBox();
        addHighScoresTitle(levelName);
        addHighScoreItems(highScores);
        hideHighestScoreText();
        showHighScoresScrollPane();
    }

    /**
     * Logs the high scores for the given level to the console.
     *
     * @param levelName the name of the level
     * @param highScores the list of high scores
     */
    private void logHighScores(String levelName, List<Integer> highScores) {
        System.out.println("High Scores for " + levelName + ": " + highScores);
    }

    /**
     * Clears the high scores VBox.
     */
    private void clearHighScoresVBox() {
        highScoresVBox.getChildren().clear();
    }

    /**
     * Adds the title for the high scores list.
     *
     * @param levelName the name of the level
     */
    private void addHighScoresTitle(String levelName) {
        highScoresVBox.getChildren().add(new Text(levelName + " High Scores:"));
    }

    /**
     * Adds the high score items to the VBox.
     *
     * @param highScores the list of high scores
     */
    private void addHighScoreItems(List<Integer> highScores) {
        for (int i = 0; i < highScores.size(); i++) {
            highScoresVBox.getChildren().add(new Text(String.format("%02d. %07d", i + 1, highScores.get(i))));
        }
    }

    /**
     * Hides the highest score text from the player.
     */
    private void hideHighestScoreText() {
        highestScoreText.setVisible(false);
    }

    /**
     * Shows the high scores scroll pane to the player.
     */
    private void showHighScoresScrollPane() {
        highScoresScrollPane.setVisible(true);
    }

    /**
     * Determines the level name based on the level data.
     *
     * <p>
     *     Gets the String value of the levelData and returns
     *     "Level 1","Level 2" or "Level 3" based on the given String value"
     * </p>
     *
     * @param levelData the data of the level
     * @return the name of the level
     */
    private String getLevelName(String[] levelData) {
        if (levelData == LevelData.Level1) {
            return "Level 1";
        } else if (levelData == LevelData.Level2) {
            return "Level 2";
        } else if (levelData == LevelData.Level3) {
            return "Level 3";
        }
        return "Unknown Level";
    }

    /**
     * Checks if the current level is the final level.
     *
     * @return True if the current level is the final level, false if it is not
     */
    private boolean isFinalLevel() {
        return levelData == LevelData.Level3;
    }

    /**
     * Handles the "Play Again" button click action to restart the game.
     *
     * @param event The action event triggered by the button click
     */
    @FXML
    private void handlePlayAgainAction(ActionEvent event) {
        System.out.println("Play Again button clicked");
        // Check if levelData is not null before attempting to reinitialize the game
        if (levelData != null) {
            gameInitializer.initializeGame(primaryStage, levelData);
        } else {
            System.err.println("Error: levelData is null");
        }
    }

    /**
     * Sets the test mode status of the game, true value indicates
     * test mode while false value indicates normal mode.
     *
     * @param testMode a boolean value to set the test mode status
     */
    public void setTestMode(boolean testMode) {
        this.isTestMode = testMode;
    }

    /**
     * Handles the "Exit Game" button click action to close the game.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleExitGameAction(ActionEvent event) {
        System.out.println("Exit Game button clicked");
        Stage stage = (Stage) exitGameBtn.getScene().getWindow();
        stage.close();
        if (!isTestMode) {
            System.exit(0);
        }
    }

    /**
     * Handles the "Back to Menu" button click action to return to the main menu.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleBackToMenuAction(ActionEvent event) {
        System.out.println("Back to Menu button clicked");
        Stage stage = (Stage) backToMenuBtn.getScene().getWindow();
        new MainScreen(stage).show();
        stage.setTitle("Main Screen");
    }

    /**
     * Handles the "Next Level" button click action to proceed to the next level.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleNextLevelAction(ActionEvent event) {
        System.out.println("Next Level button clicked");
        // Check if the current level is Level 1 and initialize the game for Level 2
        if (levelData == LevelData.Level1) {
            gameInitializer.initializeGame(primaryStage, LevelData.Level2);

            // Check if the current level is Level 2 and initialize the game for Level 3
        } else if (levelData == LevelData.Level2) {
            gameInitializer.initializeGame(primaryStage, LevelData.Level3);
        }
    }

    /**
     * Handles the "Toggle High Scores" button click action
     * to show or hide the high scores from the player.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleToggleHighScoresAction(ActionEvent event) {
        System.out.println("Toggle High Scores button clicked");
        highScoresViewed = !highScoresViewed;
        if (highScoresViewed) {
            displayHighScores(levelName);
            // Change the button text to indicate the action is to hide scores
            toggleHighScoresBtn.setText("Hide Scores");
        } else {
            displayHighestScore(levelName);
            // Change the button text to indicate the action is to view scores
            toggleHighScoresBtn.setText("View Scores");
        }
    }
}