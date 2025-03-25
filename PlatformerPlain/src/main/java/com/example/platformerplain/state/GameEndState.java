package com.example.platformerplain.state;

import com.example.platformerplain.controller.GameController;

/**
 * Represents the game end state.
 * <p>
 *     Implements the {@code GameState} interface
 *     and defines the behaviour for the game's end state.
 *     <br><br>
 *     It handles the transition to the end state and indicates that the
 *     game has ended.
 * </p>
 */
public class GameEndState implements GameState {

    /**
     * Handles input for the game end state.
     *
     * @param controller the game controller
     */
    @Override
    public void handleInput(GameController controller) {
        // No input handling needed for game end state
    }

    /**
     * Updates the game state to the end state.
     *
     * @param controller the game controller
     */
    @Override
    public void update(GameController controller) {
        // Pass false to indicate game end
        controller.handleGameStateChange(false);
    }
}
