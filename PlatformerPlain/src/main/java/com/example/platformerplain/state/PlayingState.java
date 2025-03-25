package com.example.platformerplain.state;

import com.example.platformerplain.controller.GameController;

/**
 * Represents the playing state in the game.
 * <p>
 *     Implements the {@code GameState} interface
 *     and defines the behavior for the playing state.
 *     <br><br>
 *     It handles player input and updates the game during the
 *     active gameplay phase.
 * </p>
 */
public class PlayingState implements GameState {

    /**
     * Handles input for the playing state.
     *
     * @param controller the game controller
     */
    @Override
    public void handleInput(GameController controller) {
        controller.handlePlayerInput();
    }

    /**
     * Updates the game during the playing state.
     *
     * @param controller the game controller
     */
    @Override
    public void update(GameController controller) {
        controller.updateGame();
    }
}
