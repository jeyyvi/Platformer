package com.example.platformerplain.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model for tracking the player's score.
 * <p>
 *     This ScoreModel class encapsulates the score tracking functionality,
 *     allowing the score to be updated, reset, and observed.
 *     <br><br>
 *     It maintains a list of observers that are notified whenever
 *     the score changes.
 * </p>
 */
public class ScoreModel {

    /**
     * The player's current score.
     */
    private int score;

    /**
     * The list of observers that are notified of score changes.
     */
    private final List<ScoreObserver> observers;

    /**
     * Constructs a new {@code ScoreModel} with an initial score of zero.
     */
    public ScoreModel() {
        this.score = 0;
        this.observers = new ArrayList<>();
    }

    /**
     * Returns the player's current score.
     *
     * @return The player's current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's score to the specified value and notifies observers.
     *
     * @param score the new score to set
     */
    public void setScore(int score) {
        // Update the score with the provided value
        this.score = score;
        // Notify all observers about the score change
        notifyObservers();
    }

    /**
     * Resets the player's score to zero and notifies observers.
     */
    public void reset() {
        // Set the score to zero
        this.score = 0;
        // Notify all observers about the score change
        notifyObservers();
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to add
     */
    public void addObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(ScoreObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of a score change.
     */
    private void notifyObservers() {
        // Iterate through all observers and update them with the current score
        for (ScoreObserver observer : observers) {
            observer.updateScore(score);
        }
    }
}
