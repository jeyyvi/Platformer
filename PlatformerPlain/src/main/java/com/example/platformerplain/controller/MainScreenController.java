package com.example.platformerplain.controller;

import com.example.platformerplain.util.GameInitializer;
import com.example.platformerplain.view.OptionScreen;
import com.example.platformerplain.view.InfoScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controls the main screen of the game.
 * <p>
 *     Extends {@code ScreenBaseController} and is
 *     responsible for initialisation and user interactions on the
 *     main screen, such as starting the game, changing the
 *     background colour, selecting levels, accessing the information
 *     screen, and exiting the game.
 * </p>
 */
public class MainScreenController extends ScreenBaseController {

    /**
     * The button to start the game.
     */
    @FXML
    private Button startGameBtn;

    /**
     * The combo box to select the game level.
     */
    @FXML
    ComboBox<String> levelSelector;

    /**
     * The label to display the selected level.
     */
    @FXML
    Label levelSelectedText;

    /**
     * The button to access the options screen.
     */
    @FXML
    private Button optionBtn;

    /**
     * The button to access the information screen.
     */
    @FXML
    private Button infoBtn;

    /**
     * The button to exit the game.
     */
    @FXML
    Button exitGameBtn;

    /**
     * Initialises the main screen controller.
     * <p>
     *     The {@code initialize} method is called to initialise
     *     the main screen controller. It sets both the default values
     *     for the level selector and the selected level text as "Level 1".
     * </p>
     */
    @FXML
    public void initialize() {
        super.initialize();

        levelSelector.setValue("Level 1"); // Set default value
        levelSelectedText.setText("Level 1"); // Set default text
    }

    /**
     * Handles the action of starting the game.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleStartGameAction(ActionEvent event) {
        System.out.println("Start Game button clicked");
        // Get the selected level from the level selector dropdown
        String selectedLevel = levelSelector.getValue();
        Stage primaryStage = (Stage) startGameBtn.getScene().getWindow();
        // Initialize the game with the selected level and the primary stage
        GameInitializer.initializeGame(primaryStage, selectedLevel);
    }

    /**
     * Handles the level selection action.
     *
     * @param event the action event triggered by the combo box selection
     */
    @FXML
    private void handleLevelSelection(ActionEvent event) {
        String selectedLevel = levelSelector.getValue();
        levelSelectedText.setText(selectedLevel);
    }

    /**
     * Handles the action of accessing the options screen.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleOptionAction(ActionEvent event) {
        System.out.println("Option button clicked");
        Stage primaryStage = (Stage) optionBtn.getScene().getWindow();
        // Create and show the OptionScreen on the primary stage
        new OptionScreen(primaryStage).show();
    }

    /**
     * Handles the action of accessing the information screen.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleInfoAction(ActionEvent event) {
        System.out.println("Info button clicked");
        Stage primaryStage = (Stage) infoBtn.getScene().getWindow();
        // Create and show the InfoScreen on the primary stage
        new InfoScreen(primaryStage).show();
    }

    /**
     * Handles the action of exiting the game.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleExitGameAction(ActionEvent event) {
        System.out.println("Exit Game button clicked");
        Stage primaryStage = (Stage) exitGameBtn.getScene().getWindow();
        // Close the primary stage to exit the game
        primaryStage.close();
    }
}
