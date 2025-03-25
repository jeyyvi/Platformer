package com.example.platformerplain.view;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.util.ScreenFactory;
import javafx.stage.Stage;

/**
 * Represents the end screen in the game.
 * <p>
 *     The {@code EndScreen} class implements the 'Screen' interface and specifies
 *     the behaviour of the end screen.
 *     <br><br>
 *     It sets up the end screen with the primary stage and provides a
 *     method to display the screen.
 * </p>
 */
public class EndScreen implements Screen {

    /**
     * The primary stage of the application.
     */
    private Stage primaryStage;

    /**
     * The {@code EndScreen} class constructs a new {@code EndScreen} with the
     * specified primary stage.
     *
     * @param primaryStage the primary stage of the application
     */
    public EndScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Displays the end screen.
     */
    @Override
    public void show() {
        ScreenFactory.createScreen(ScreenType.END_SCREEN, primaryStage, 0, null, false);
    }
}
