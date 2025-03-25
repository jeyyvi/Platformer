package com.example.platformerplain.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the eagle enemy in the game.
 * <p>
 *     The {@code Eagle} class encapsulates the eagle enemy's attributes
 *     and behaviours, such as visual representation, velocity,
 *     and movement methods.
 *     <br><br>
 *     The eagle can move horizontally and vertically within the game.
 * </p>
 */
public class Eagle {

    /**
     * The visual representation of the eagle.
     */
    private final ImageView entity;

    /**
     * The velocity of the eagle.
     */
    private Point2D velocity;

    /**
     * Constructs a new {@code Eagle} with an initial image and velocity.
     * <p>
     *     The Eagle constructor initializes the eagle's visual representation
     *     with an image and sets its initial size and velocity.
     * </p>
     */
    public Eagle() {
        Image eagleImage = new Image(getClass().getResourceAsStream("/Image/Eagle.png"));
        this.entity = new ImageView(eagleImage);
        this.entity.setFitWidth(150);
        this.entity.setFitHeight(150);
        this.velocity = new Point2D(0, 0);
    }

    /**
     * Returns the visual representation of the eagle.
     *
     * @return The eagle's visual representation
     */
    public ImageView getEntity() {
        return entity;
    }

    /**
     * Returns the velocity of the eagle.
     *
     * @return The eagle's velocity
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the eagle.
     *
     * @param velocity the new velocity of the eagle
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    /**
     * Moves the eagle horizontally by the specified value.
     *
     * @param value the distance to move the eagle horizontally
     */
    public void moveX(int value) {
        entity.setTranslateX(entity.getTranslateX() + value);
    }

    /**
     * Moves the eagle vertically by the specified value.
     *
     * @param value the distance to move the eagle vertically
     */
    public void moveY(int value) {
        entity.setTranslateY(entity.getTranslateY() + value);
    }
}
