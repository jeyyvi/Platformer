package com.example.platformerplain.view;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.util.ScreenFactory;
import javafx.stage.Stage;

/**
 * Represents the information screen in the game.
 * <p>
 *     Implements the {@code Screen} interface and
 *     defines the behaviour of the information screen.
 *     <br><br>
 *     It sets up the information screen with the primary stage
 *     and provides a method to display the screen.
 * </p>
 */
public class InfoScreen implements Screen {

    /**
     * The primary stage of the application.
     */
    private Stage primaryStage;

    /**
     * The InfoScreen class constructs a new {@code InfoScreen} with the specified primary stage.
     *
     * @param primaryStage the primary stage of the application
     */
    public InfoScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Displays the information screen.
     */
    @Override
    public void show() {
        ScreenFactory.createScreen(ScreenType.INFO_SCREEN, primaryStage, 0, null, false);
    }
}
