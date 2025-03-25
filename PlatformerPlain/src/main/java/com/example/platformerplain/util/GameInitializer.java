package com.example.platformerplain.util;

import com.example.platformerplain.controller.GameController;
import com.example.platformerplain.model.LevelData;
import com.example.platformerplain.model.Player;
import com.example.platformerplain.view.GameView;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Provides utility methods to initialize and start the game.
 * <p>
 *     The {@code GameInitializer} class contains static methods to set up
 *     and initialise the game environment, including creating the
 *     game view, player, game controller, and scene.
 *     <br><br>
 *     It also manages loading level data and configuring the primary stage.
 * </p>
 */
public class GameInitializer {

    /**
     * Initializes the game with the default level.
     *
     * @param primaryStage the primary stage of the application
     */
    public static void initializeGame(Stage primaryStage) {
        initializeGame(primaryStage, "Level 1"); // Default to Level 1
    }

    /**
     * Initialises the game with the specified level.
     *
     * @param primaryStage the primary stage of the application
     * @param level the title of the level to load
     */
    public static void initializeGame(Stage primaryStage, String level) {
        String[] levelData = getLevelData(level);
        initializeGame(primaryStage, levelData);
    }

    /**
     * Initializes the game with the specified level data.
     *
     * @param primaryStage the primary stage of the application
     * @param levelData the array of strings representing the level layout
     */
    public static void initializeGame(Stage primaryStage, String[] levelData) {
        GameView gameView = createGameView();
        Player player = createPlayer();
        GameController gameController = createGameController(gameView, player, primaryStage, levelData);
        Scene gameScene = createGameScene(gameView, gameController, primaryStage);

        setupStage(primaryStage, gameScene);
        gameController.startGame();
    }

    /**
     * Returns the level data for the specified level.
     *
     * @param level the name of the level
     *
     * @return The array of strings representing the level layout
     */
    private static String[] getLevelData(String level) {
        switch (level) {
            case "Level 2":
                return LevelData.Level2;
            case "Level 3":
                return LevelData.Level3;
            case "Level 1":
            default:
                return LevelData.Level1;
        }
    }

    /**
     * Creates a new game view.
     *
     * @return A new {@code GameView} instance
     */
    private static GameView createGameView() {
        return new GameView();
    }

    /**
     * Creates a new player.
     *
     * @return A new {@code Player} instance
     */
    private static Player createPlayer() {
        return new Player(0, 500, 70, 70, "/Image/Chicken.png");
    }

    /**
     * Creates a new game controller.
     *
     * @param gameView the game view
     * @param player the player
     * @param primaryStage the primary stage of the application
     * @param levelData the array of strings representing the level layout
     *
     * @return A new {@code GameController} instance
     */
    private static GameController createGameController(GameView gameView, Player player, Stage primaryStage, String[] levelData) {
        return new GameController(gameView, player, primaryStage, levelData);
    }

    /**
     * Creates a new game scene.
     *
     * @param gameView the game view
     * @param gameController the game controller
     * @param primaryStage the primary stage of the application
     *
     * @return A new {@code Scene} instance
     */
    private static Scene createGameScene(GameView gameView, GameController gameController, Stage primaryStage) {
        // Retrieve the current dimensions of the primary stage
        double currentWidth = primaryStage.getWidth();
        double currentHeight = primaryStage.getHeight();

        Scene gameScene = new Scene(gameView.getAppRoot(), currentWidth, currentHeight);
        // Attach event handlers to handle key presses
        // When a key is pressed, update the GameController's InputHandler to register it as active
        gameScene.setOnKeyPressed(e -> gameController.getInputHandler().setKeyPressed(e.getCode(), true));

        // Attach event handlers to handle key releases
        // When a key is released, update the InputHandler to mark it as inactive
        gameScene.setOnKeyReleased(e -> gameController.getInputHandler().setKeyPressed(e.getCode(), false));

        return gameScene;
    }

    /**
     * Sets up the primary stage with the specified game scene.
     *
     * @param primaryStage the primary stage of the application
     * @param gameScene the game scene to set
     */
    private static void setupStage(Stage primaryStage, Scene gameScene) {
        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Platformer");
        primaryStage.show();
    }
}
