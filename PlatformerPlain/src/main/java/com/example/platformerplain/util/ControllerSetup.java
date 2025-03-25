package com.example.platformerplain.util;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.controller.EndScreenController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Provides a utility for initializing controllers based on screen types.
 * <p>
 *     The ControllerSetup class has a static method for initialising
 *     different controllers depending on the screen type.
 *     <br><br>
 *     It configures controllers with the necessary data and configurations
 *     for each specific screen.
 * </p>
 */
public class ControllerSetup {

    /**
     * Default constructor for {@code ControllerSetup}.
     * Initializes the utility class.
     */
    public ControllerSetup() {
    }

    /**
     * Initialises the controller for the specified screen type.
     *
     * @param screenType the type of the screen to initialize
     * @param loader the FXMLLoader used to load the screen
     * @param primaryStage the primary stage of the application
     * @param finalScore the final score to set in the controller
     * @param levelData the level data to set in the controller
     * @param levelCompleted the level completion status to set in the controller
     */
    public static void initializeController(ScreenType screenType, FXMLLoader loader, Stage primaryStage, int finalScore, String[] levelData, boolean levelCompleted) {
        if (screenType == ScreenType.END_SCREEN) {
            EndScreenController controller = loader.getController();
            controller.setStage(primaryStage);
            controller.setLevelData(levelData);
            controller.setLevelCompleted(levelCompleted);
            controller.setFinalScore(finalScore);
        }
    }
}
