package com.example.platformerplain.controller;

import com.example.platformerplain.state.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the GameStateController class.
 *
 * It verifies the following functionalities:
 * - Initial state of the game.
 * - Transition to the game end state.
 * - Transition to the level pass state.
 *
 * @see GameStateController
 */
public class GameStateControllerTest {
    private GameStateController gameStateController;
    private GameState playingState;
    private GameState gameEndState;
    private GameState levelPassState;

    /**
     * Sets up the test environment before each test.
     * Initializes the GameStateController and retrieves the game states.
     */
    @BeforeEach
    public void setUp() {
        gameStateController = new GameStateController(null); // Pass null for the controller, as we only test states here
        playingState = gameStateController.getPlayingState();
        gameEndState = gameStateController.getGameEndState();
        levelPassState = gameStateController.getLevelPassState();
    }

    /**
     * Tests the initial state of the game.
     * Ensures the initial state is the playing state.
     */
    @Test
    public void testInitialState() {
        assertEquals(playingState, gameStateController.getCurrentState(), "Initial state should be playingState.");
    }

    /**
     * Tests the transition to the game end state.
     */
    @Test
    public void testGameEndStateTransition() {
        gameStateController.setState(gameEndState);
        assertEquals(gameEndState, gameStateController.getCurrentState(), "Current state should be gameEndState.");
    }

    /**
     * Tests the transition to the level pass state.
     */
    @Test
    public void testLevelPassStateTransition() {
        gameStateController.setState(levelPassState);
        assertEquals(levelPassState, gameStateController.getCurrentState(), "Current state should be levelPassState.");
    }
}
