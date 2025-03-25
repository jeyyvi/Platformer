package com.example.platformerplain.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a snake enemy in the game.
 * <p>
 *     The {@code Snake} class represents the attributes and behaviours
 *     of a snake enemy, including its visual representation
 *     and position in the game.
 *     <br><br>
 *     Snakes are obstacles that players must avoid.
 * </p>
 */
public class Snake {

    /**
     * The visual representation of the snake.
     */
    private ImageView entity;

    /**
     * The Snake class constructs a new 'Snake' at the given x and y coordinates.
     *
     * @param x the initial X coordinate of the snake
     * @param y the initial Y coordinate of the snake
     */
    public Snake(double x, double y) {
        Image snakeImage = new Image(getClass().getResourceAsStream("/Image/snake.png"));
        entity = new ImageView(snakeImage);
        entity.setFitWidth(50);
        entity.setFitHeight(50);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
    }

    /**
     * Returns the visual representation of the snake.
     *
     * @return The snake's visual representation
     */
    public ImageView getEntity() {
        return entity;
    }
}
