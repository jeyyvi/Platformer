package com.example.platformerplain.controller;

import com.example.platformerplain.model.Player;
import com.example.platformerplain.view.GameView;
import javafx.scene.Node;

/**
 * Updates the game state and handles player movements, collisions, and level progression.
 *
 * <p>
 *     The {@code GameUpdater} class updates the game state throughout each game loop iteration.
 *     It controls player movement, screen scrolling, collision detection,
 *     and level completion. It ensures that the game logic is executed and
 *     the game state is accurately maintained.
 * </p>
 */
public class GameUpdater {

    /**
     * Represents the player character.
     */
    private final Player player;

    /**
     * Represents the game view.
     * <p>
     *     Responsible for rendering the game's visual elements such as
     *     player, eagle, snake, platforms, and user interface.
     * </p>
     */
    private final GameView view;

    /**
     * Manages the core game logic and interactions.
     */
    private final GameController gameController;

    /**
     * Manages the game state transitions and updates.
     * <p>
     *     Responsible for managing the game state transitions,
     *     such as switching between playing, game end,
     *     and level pass states.
     * </p>
     */
    private final GameStateController gameStateManager;

    /**
     * Manages the player's score.
     */
    private final ScoreController scoreController;

    /**
     * Loads and initialises the game levels.
     */
    private final LevelLoader levelLoader;

    /**
     * Constructs a new 'GameUpdater' with the specified
     * parameters - gameController, player, view, gameStateManager, scoreController and levelLoader
     *
     * @param gameController the game controller managing the game
     * @param player the player character
     * @param view the game view for rendering
     * @param gameStateManager the game state manager
     * @param scoreController the score controller
     * @param levelLoader the level loader
     */
    public GameUpdater(GameController gameController, Player player, GameView view, GameStateController gameStateManager, ScoreController scoreController, LevelLoader levelLoader) {
        this.gameController = gameController;
        this.player = player;
        this.view = view;
        this.gameStateManager = gameStateManager;
        this.scoreController = scoreController;
        this.levelLoader = levelLoader;
    }

    /**
     * Updates the game state, including the player's movement,
     * screen scrolling, collision checks, and level completion.
     */
    public void updateGame() {
        updatePlayerMovement();
        scrollScreen();
        checkPlayerFall();
        checkEagleCollision();
        checkLevelCompletion();
        scoreController.update();
    }

    /**
     * Updates the player's movement based on their velocity.
     */
    private void updatePlayerMovement() {
        gameController.getPlayerController().applyGravity();
        gameController.getPlayerController().moveY((int) player.getVelocity().getY());
    }

    /**
     * Scrolls the screen based on the player's position.
     */
    private void scrollScreen() {
        // Get the player's current X-coordinate on the screen
        int playerX = (int) player.getEntity().getTranslateX();
        // Check if the player's position exceeds the scroll threshold (640 pixels)
        if (playerX > 640) {
            view.getGameRoot().setLayoutX(-(playerX - 640));
        }
    }

    /**
     * Checks if the player has fallen off the screen.
     * <p>
     *     If the player falls off the screen, the game set
     *     state will be set to the game end state and the
     *     game end logic will be triggered.
     * </p>
     */
    private void checkPlayerFall() {
        // Check if the player's Y-coordinate has moved below the visible game area
        if (player.getEntity().getTranslateY() > view.getGameRoot().getHeight()) {
            // Set the game state to "Game End" when the player falls out of bounds
            gameStateManager.setState(gameStateManager.getGameEndState());
            gameStateManager.update(gameController);  // Trigger game end state
        }
    }

    /**
     * Checks for collisions between the player and the eagle.
     */
    private void checkEagleCollision() {
        gameController.getEagleController().checkEagleCollision(gameController);
    }

    /**
     * Checks if the player has reached the end of the level.
     *
     * <p>
     *     If the player reached the end of the level,
     *     the level will be marked complete and the level pass logic
     *     will be triggered.
     * </p>
     */
    private void checkLevelCompletion() {
        // Iterate through all endpoints defined in the level
        for (Node endpoint : levelLoader.getEndpoints()) {
            // Check if the player's bounds intersect with the endpoint's bounds
            if (player.getEntity().getBoundsInParent().intersects(endpoint.getBoundsInParent())) {
                scoreController.handleLevelPass();
                gameStateManager.setState(gameStateManager.getLevelPassState());
                gameStateManager.update(gameController);  // Trigger level pass state
                break;
            }
        }
    }
}
