package com.example.platformerplain.view;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.util.ScreenFactory;
import javafx.stage.Stage;

/**
 * Represents the options screen in the game.
 * <p>
 *     Implements the {@code Screen} interface and
 *     specifies the behaviour of the options screen.
 *     <br><br>
 *     It sets up the options screen with the primary stage and
 *     provides a method to display the screen.
 * </p>
 */
public class OptionScreen implements Screen {

    /**
     * The primary stage of the application.
     */
    private Stage primaryStage;

    /**
     * The {@code OptionScreen} class constructs a new {@code OptionScreen} with the
     * specified primary stage.
     *
     * @param primaryStage the primary stage of the application
     */
    public OptionScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Displays the options screen.
     */
    @Override
    public void show() {
        ScreenFactory.createScreen(ScreenType.OPTION_SCREEN, primaryStage, 0, null, false);
    }
}
