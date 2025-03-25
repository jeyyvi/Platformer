package com.example.platformerplain.controller;

import com.example.platformerplain.config.ScreenType;
import com.example.platformerplain.state.GameState;
import com.example.platformerplain.state.GameEndState;
import com.example.platformerplain.state.LevelPassState;
import com.example.platformerplain.state.PlayingState;
import com.example.platformerplain.util.ScreenFactory;
import javafx.application.Platform;

/**
 * Manages the game state transitions and updates.
 * <p>
 *     The {@code GameStateController} class handles the switching between
 *     different game states, such as playing, game end, and level pass.
 *     It coordinates the game logic based on the current state and
 *     updates the game controller accordingly.
 * </p>
 */
public class GameStateController {

    /**
     * The current state of the game.
     */
    public GameState currentState;

    /**
     * The game state representing the playing state.
     */
    private final GameState playingState;

    /**
     * The game state representing the game end state.
     */
    private final GameState gameEndState;

    /**
     * The game state representing the level pass state.
     */
    private final GameState levelPassState;

    /**
     * Constructs a new {@code GameStateController} with the specified game controller.
     *
     * @param controller the game controller managing the game state
     */
    public GameStateController(GameController controller) {
        this.playingState = new PlayingState();
        this.gameEndState = new GameEndState();
        this.levelPassState = new LevelPassState();
        this.currentState = playingState;
    }

    /**
     * Sets the current game state to the specified state.
     *
     * @param state the new game state to set
     */
    public void setState(GameState state) {
        this.currentState = state;
    }

    /**
     * Returns the playing state of the game.
     *
     * @return the playing state
     */
    public GameState getPlayingState() {
        return playingState;
    }

    /**
     * Returns the game end state.
     *
     * @return the game end state
     */
    public GameState getGameEndState() {
        return gameEndState;
    }

    /**
     * Returns the level pass state.
     *
     * @return the level pass state
     */
    public GameState getLevelPassState() {
        return levelPassState;
    }

    /**
     * Updates the game controller based on the current state.
     *
     * @param controller the game controller to update
     */
    public void update(GameController controller) {
        currentState.handleInput(controller);
        currentState.update(controller);
    }

    /**
     * Handles the transition between game states, such as passing
     * a level or ending the game.
     *
     * @param controller the game controller managing the game state
     * @param levelPassed true if the level was passed, false if it is not
     * @param levelPattern the pattern of the levels
     */
    public void handleGameStateChange(GameController controller, boolean levelPassed, String[] levelPattern) {
        // Determine the message to display based on whether the level was passed or not
        String message = levelPassed ? "Level Passed. Proceeding to the next level." : "Game Over. Displaying game over screen.";
        System.out.println(message);

        int finalScore = controller.getScoreController().getScore();
        Platform.runLater(() -> {
            // Create and display the end screen with the relevant parameters
            ScreenFactory.createScreen(ScreenType.END_SCREEN, controller.getPrimaryStage(), finalScore, levelPattern, levelPassed);
        });

        controller.stopGameTimer();
    }


    /**
     * Returns the current state of the game.
     *
     * @return The current state of the game
     */
    public GameState getCurrentState() {
        return this.currentState;
    }
}
