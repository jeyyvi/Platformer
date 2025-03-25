package com.example.platformerplain.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the player character in the game.
 * <p>
 *     The {@code Player} this class represents the player character's attributes
 *     and behaviours, such as visual representation, velocity,
 *     jumping abilities, and position.
 * </p>
 */
public class Player {

    /**
     * The visual representation of the player.
     */
    private ImageView entity;

    /**
     * The velocity of the player.
     */
    private Point2D velocity;

    /**
     * Indicates whether the player can jump.
     */
    private boolean canJump;

    /**
     * The last recorded position of the player.
     */
    private Point2D lastPosition;

    /**
     * The Player class constructs a new {@code Player} with the given parameters -
     * x, y, width, height and imagePath.
     *
     * @param x the initial X coordinate of the player
     * @param y the initial Y coordinate of the player
     * @param width the width of the player
     * @param height the height of the player
     * @param imagePath the file path to the player's image
     */
    public Player(int x, int y, int width, int height, String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        this.entity = new ImageView(image);
        this.entity.setFitWidth(width);
        this.entity.setFitHeight(height);
        this.entity.setTranslateX(x);
        this.entity.setTranslateY(y);
        this.velocity = new Point2D(0, 0);
        this.canJump = true;
        this.lastPosition = new Point2D(x, y);
    }

    /**
     * Returns the visual representation of the player.
     *
     * @return The player's visual representation
     */
    public ImageView getEntity() {
        return entity;
    }

    /**
     * Returns the velocity of the player.
     *
     * @return The player's velocity
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the player.
     *
     * @param velocity the new velocity of the player
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    /**
     * Checks if the player can jump.
     *
     * @return True if the player can jump, false if can't
     */
    public boolean canJump() {
        return canJump;
    }

    /**
     * Sets the player's ability to jump.
     *
     * @param canJump true to allow the player to jump, false to not allow
     */
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    /**
     * Checks if the player is moving.
     *
     * @return True if the player is moving, false if it is not
     */
    public boolean isMoving() {
        boolean isMoving = !lastPosition.equals(new Point2D(entity.getTranslateX(), entity.getTranslateY()));
        lastPosition = new Point2D(entity.getTranslateX(), entity.getTranslateY());
        return isMoving;
    }
}
