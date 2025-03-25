package com.example.platformerplain.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a fireball projectile in the game.
 * <p>
 *     The {@code Fireball} class encapsulates the attributes and behaviours
 *     of a fireball, such as its visual representation, speed, and
 *     methods for updating its position.
 *     <br><br>
 *     The fireball can move horizontally throughout the game.
 * </p>
 */
public class Fireball {

    /**
     * The visual representation of the fireball.
     */
    private ImageView entity;

    /**
     * The speed of the fireball.
     */
    private double speed;

    /**
     * The {@code Fireball} class constructs a new {@code Fireball} with the given parameters -
     * startX, startY, speed and imagePath.
     *
     * @param startX the initial X coordinate of the fireball
     * @param startY the initial Y coordinate of the fireball
     * @param speed the speed of the fireball
     * @param imagePath the file path to the fireball image
     */
    public Fireball(double startX, double startY, double speed, String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        this.entity = new ImageView(image);
        this.entity.setFitWidth(20);
        this.entity.setFitHeight(20);
        this.entity.setTranslateX(startX);
        this.entity.setTranslateY(startY);
        this.speed = speed;
    }

    /**
     * Returns the visual representation of the fireball.
     *
     * @return The fireball's visual representation
     */
    public ImageView getEntity() {
        return entity;
    }

    /**
     * Sets the speed of the fireball.
     *
     * @return The speed of the fireball
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Updates the position of the fireball.
     *
     * @param speed the new speed of the fireball
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Updates the position of the fireball.
     */
    public void updatePosition() {
        entity.setTranslateX(entity.getTranslateX() + speed);
    }
}
