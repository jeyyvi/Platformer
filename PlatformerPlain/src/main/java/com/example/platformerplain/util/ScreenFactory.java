package com.example.platformerplain.util;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.view.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Factory class for creating and configuring screens.
 * <p>
 *     The {@code ScreenFactory} class contains static methods for building and
 *     configuring different types of screens based on the given screen type.
 *     <br><br>
 *     It is responsible for setting up the screen controller and user interface.
 * </p>
 */
public class ScreenFactory {

    /**
     *Creates and configures a screen based on the specified screen type.
     *
     * @param screenType the type of the screen to create
     * @param primaryStage the primary stage of the application
     * @param finalScore the final score to set in the controller
     * @param levelData the level data to set in the controller
     * @param levelCompleted the level completion status to set in the controller
     *
     * @return The created and configured screen
     */
    public static Screen createScreen(ScreenType screenType, Stage primaryStage, int finalScore, String[] levelData, boolean levelCompleted) {
        Screen screen = createScreenInstance(screenType, primaryStage);
        configureScreen(screenType, primaryStage, finalScore, levelData, levelCompleted);
        return screen;
    }

    /**
     * Creates an instance of the screen based on the specified screen type.
     *
     * @param screenType the type of the screen to create
     * @param primaryStage the primary stage of the application
     *
     * @return The created screen instance
     * @throws IllegalArgumentException If the screen type is unknown
     */
    private static Screen createScreenInstance(ScreenType screenType, Stage primaryStage) {
        switch (screenType) {
            case MAIN_SCREEN:
                return new MainScreen(primaryStage);
            case OPTION_SCREEN:
                return new OptionScreen(primaryStage);
            case INFO_SCREEN:
                return new InfoScreen(primaryStage);
            case END_SCREEN:
                return new EndScreen(primaryStage);
            default:
                throw new IllegalArgumentException("Unknown screen type: " + screenType);
        }
    }

    /**
     * Configures the screen based on the specified screen type and parameters.
     *
     * @param screenType the type of the screen to configure
     * @param primaryStage the primary stage of the application
     * @param finalScore the final score to set in the controller
     * @param levelData the level data to set in the controller
     * @param levelCompleted the level completion status to set in the controller
     */
    private static void configureScreen(ScreenType screenType, Stage primaryStage, int finalScore, String[] levelData, boolean levelCompleted) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenFactory.class.getResource(screenType.getFXMLFilePath()));
            Parent root = loader.load();

            // Use the current width and height of the primary stage
            double currentWidth = primaryStage.getWidth();
            double currentHeight = primaryStage.getHeight();

            // Set the new scene with the loaded FXML and update the stage's title
            primaryStage.setScene(new Scene(root, currentWidth, currentHeight));
            primaryStage.setTitle(screenType.getTitle());
            primaryStage.show();

            // Delegate controller setup to ControllerSetup
            ControllerSetup.initializeController(screenType, loader, primaryStage, finalScore, levelData, levelCompleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
