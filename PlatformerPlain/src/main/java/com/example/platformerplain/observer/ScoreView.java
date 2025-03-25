package com.example.platformerplain.observer;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Displays the player's score in the game UI.
 * <p>
 *     Implements the {@code ScoreObserver} interface, which
 *     updates the score display when the player's score changes.
 *     <br><br>
 *     The score is displayed as formatted text in the game's UI.
 * </p>
 */
public class ScoreView implements ScoreObserver {

    /**
     * The text element for displaying the score.
     */
    private final Text scoreText;

    /**
     * The ScoreView class constructs a new 'ScoreView' and
     * initialises the score display.
     *
     * @param uiRoot the root pane of the game UI
     */
    public ScoreView(Pane uiRoot) {
        this.scoreText = new Text(10, 20, "Score: 0000000");
        this.scoreText.setFont(new Font(20));
        uiRoot.getChildren().add(scoreText);
    }

    /**
     * Updates the score display with the new score.
     *
     * @param score the new score value
     */
    @Override
    public void updateScore(int score) {
        scoreText.setText(String.format("Score: %07d", score));
    }
}
