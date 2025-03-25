package com.example.platformerplain.state;

import com.example.platformerplain.controller.GameController;

/**
 * Represents a state in the game.
 * <p>
 *     The {@code GameState} interface defines the methods for handling input
 *     and updating the game's state.
 *     <br><br>
 *     Implementing classes must provide implementations of these methods
 *     in order to provide different behaviours for different game states.
 * </p>
 */
public interface GameState {

    /**
     * Handles input for the current game state.
     *
     * @param controller the game controller
     */
    void handleInput(GameController controller);

    /**
     * Updates the current game state.
     *
     * @param controller the game controller
     */
    void update(GameController controller);
}
