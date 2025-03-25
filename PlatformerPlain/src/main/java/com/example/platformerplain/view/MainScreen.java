package com.example.platformerplain.view;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.util.ScreenFactory;
import javafx.stage.Stage;

/**
 * Represents the main screen in the game.
 * <p>
 *     Implements the {@code Screen} interface and
 *     controls the behaviour of the main screen.
 *     <br><br>
 *     It sets up the main screen with the primary stage and provides
 *     a method to display the screen.
 * </p>
 */
public class MainScreen implements Screen {

    /**
     * The primary stage of the application.
     */
    private Stage primaryStage;

    /**
     * The {@code MainScreen} class constructs a new {@code MainScreen}
     * with the specified primary stage.
     *
     * @param primaryStage The primary stage of the application
     */
    public MainScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Displays the main screen.
     */
    @Override
    public void show() {
        ScreenFactory.createScreen(ScreenType.MAIN_SCREEN, primaryStage, 0, null, false);
    }
}
