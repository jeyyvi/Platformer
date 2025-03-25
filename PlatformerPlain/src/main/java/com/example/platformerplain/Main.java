package com.example.platformerplain;

import com.example.platformerplain.view.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the game application.
 * <p>
 *     The {@code Main} class extends Application, it serves as the
 *     main entry point for launching the game application.
 *     <br><br>
 *     It initialises the primary stage and shows the main screen.
 * </p>
 */
public class Main extends Application {

    /**
     * Starts the game application.
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        // Show the main screen first
        new MainScreen(primaryStage).show();
    }

    /**
     * The main method for launching the game application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
