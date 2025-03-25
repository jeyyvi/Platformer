package com.example.platformerplain.controller;

import com.example.platformerplain.observer.ScoreModel;
import com.example.platformerplain.observer.ScoreView;
import com.example.platformerplain.model.Player;

/**
 * Manages the player's score during the game.
 * <p>
 *     The {@code ScoreController} class is responsible for
 *     updating the player's score in response to various
 *     game events, such as collecting money, colliding with
 *     snakes, and passing levels.
 *     <br><br>
 *     It uses the {@code ScoreModel} to track the score and updates
 *     the score view accordingly.
 * </p>
 */
public class ScoreController {

    // Introduce constants to replace magic numbers
    /**
     * The score increment for collecting a money item.
     */
    private static final int SCORE_INCREMENT = 100;

    /**
     * The score decrement for colliding with a snake.
     */
    private static final int SNAKE_SCORE_DECREMENT = 100;

    /**
     * The score increment for passing a level.
     */
    private static final int LEVEL_PASS_SCORE_INCREMENT = 500;

    /**
     * The model for tracking the player's score.
     */
    private final ScoreModel scoreModel;

    /**
     * Represents the player character.
     */
    private final Player player;

    /**
     * Manages the collection and interactions of money items.
     */
    private final MoneyController moneyController;

    /**
     * Constructs a new {@code ScoreController} with the given parameters -
     * scoreModel, scoreView, player and moneyController.
     * <p>
     *     It also adds the score view as an observer to the score model,
     *     ensuring that the score view is updated whenever the score changes.
     * </p>
     *
     * @param scoreModel the model for tracking the player's score
     * @param scoreView the view for displaying the player's score
     * @param player the player character
     * @param moneyController the controller for managing money items
     */
    public ScoreController(ScoreModel scoreModel, ScoreView scoreView, Player player, MoneyController moneyController) {
        this.scoreModel = scoreModel;
        this.player = player;
        this.moneyController = moneyController;
        // Add the view as an observer
        this.scoreModel.addObserver(scoreView);
    }

    /**
     * Updates the player's score based on collected money items.
     * <p>
     *     The {@code update} method checks for money items collected by the
     *     player, increase the score by 100 for each collected money
     *     item, and updates the score model accordingly.
     * </p>
     */
    public void update() {
        int collectedMoney = moneyController.collectMoney(player);
        // If any money was collected, increment the player's score
        if (collectedMoney > 0) {
            scoreModel.setScore(scoreModel.getScore() + (collectedMoney * SCORE_INCREMENT)); // Increment score by 100 for each collected money
        }
    }

    /**
     * Handles the event of the player colliding with a snake.
     * <p>
     *     The {@code handleSnakeCollision} method decrease the player's
     *     score by 100 when it detects a collision of the player with a snake.
     * </p>
     */
    public void handleSnakeCollision() {
        scoreModel.setScore(scoreModel.getScore() - SNAKE_SCORE_DECREMENT);
    }

    /**
     * Handles the event of the player passing a level.
     * <p>
     *     The {@code handleLevelPass} method increase the player's
     *     score by 500 when the player passes a level.
     * </p>
     */
    public void handleLevelPass() {
        scoreModel.setScore(scoreModel.getScore() + LEVEL_PASS_SCORE_INCREMENT);
    }

    /**
     * Returns the player's current score.
     *
     * @return The player's current score.
     */
    public int getScore() {
        return scoreModel.getScore();
    }

    /**
     * Returns the score model used by this controller.
     *
     * @return The score model used by this controller.
     */
    public ScoreModel getScoreModel() {
        return scoreModel;
    }
}
