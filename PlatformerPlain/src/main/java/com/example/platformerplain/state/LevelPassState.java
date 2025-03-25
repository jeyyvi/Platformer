package com.example.platformerplain.state;

import com.example.platformerplain.controller.GameController;

/**
 * Represents the level pass state.
 * <p>
 *     Implements the {@code GameState} interface
 *     and specifies the behaviour of the level pass state.
 *     <br><br>
 *     It manages the transition to the next level and indicates
 *     that the current level has been completed successfully.
 * </p>
 */
public class LevelPassState implements GameState {

    /**
     * Handles input for the level pass state.
     *
     * @param controller the game controller
     */
    @Override
    public void handleInput(GameController controller) {
        // No input handling needed for level pass state
    }

    /**
     * Updates the game state to the level pass state.
     *
     * @param controller the game controller
     */
    @Override
    public void update(GameController controller) {
        // Pass true to indicate level passed
        controller.handleGameStateChange(true);
    }
}
