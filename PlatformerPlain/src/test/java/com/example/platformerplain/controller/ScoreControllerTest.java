package com.example.platformerplain.controller;

import com.example.platformerplain.observer.ScoreModel;
import com.example.platformerplain.observer.ScoreView;
import com.example.platformerplain.model.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the ScoreController class.
 *
 * It verifies the following functionalities:
 * - Initializing the ScoreController.
 * - Updating the score
 * - Handling score decrement on snake collision.
 * - Handling score increment on level pass.
 *
 * @see ScoreController
 */
public class ScoreControllerTest {
    private ScoreController scoreController;
    private ScoreModel scoreModel;
    private Player player;
    private Pane uiRoot;
    private MoneyController moneyController;

    /**
     * Sets up the test environment before each test.
     * Initializes the Player, ScoreModel, MoneyController, and ScoreController.
     */
    @BeforeEach
    public void setUp() {
        uiRoot = new Pane(); // Initialize uiRoot
        scoreModel = new ScoreModel();
        player = new Player(0, 500, 70, 70, "/Image/Chicken.png");
        moneyController = new MoneyController(new ArrayList<>(), player.getEntity());
        ScoreView scoreView = new ScoreView(uiRoot); // Pass uiRoot to ScoreView
        scoreController = new ScoreController(scoreModel, scoreView, player, moneyController);

        // Add player to the uiRoot
        uiRoot.getChildren().add(player.getEntity());
    }

    /**
     * Tests that the ScoreController initializes correctly with the provided parameters.
     */
    @Test
    public void testScoreUpdate() {
        scoreModel.setScore(100);
        scoreController.update();
        assertEquals(100, scoreModel.getScore());
    }

    /**
     * Tests that the score resets correctly when reset method is called.
     */
    @Test
    public void testScoreReset() {
        scoreModel.setScore(100);
        scoreModel.reset();
        assertEquals(0, scoreModel.getScore());
    }

    /**
     * Tests that the score decrements by 100 points when the player collides with a snake.
     */
    @Test
    public void testScoreDecrementOnSnakeCollision() {
        scoreModel.setScore(500);
        scoreController.handleSnakeCollision();
        assertEquals(400, scoreModel.getScore());
    }

    /**
     * Tests that the score increments by 500 points when a level is passed.
     */
    @Test
    public void testScoreIncrementOnLevelPass() {
        scoreModel.setScore(1000);
        scoreController.handleLevelPass();
        assertEquals(1500, scoreModel.getScore());
    }
}
