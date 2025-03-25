package com.example.platformerplain.observer;

/**
 * Observes changes in the player's score.
 * <p>
 *     The {@code ScoreObserver} interface specifies a method for tracking
 *     changes to the player's score.
 *     <br><br>
 *     Implementing classes must include an implementation of the
 *     {@code updateScore} function, which is called anytime the score changes.
 * </p>
 */
public interface ScoreObserver {

    /**
     * Updates the score.
     *
     * @param score the new score value
     */
    void updateScore(int score);
}
