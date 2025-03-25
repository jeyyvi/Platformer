package com.example.platformerplain.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a money item in the game.
 * <p>
 *     The {@code Money} class encapsulates a money item's attributes and behaviours,
 *     such as its visual representation and position in the game.
 *     <br><br>
 *     Money items can be collected by the player to increase their score.
 * </p>
 */
public class Money {

    /**
     * The visual representation of the money item.
     */
    private ImageView entity;

    /**
     * Constructs a new {@code Money} item at the given coordinates, x and y.
     *
     * @param x the initial X coordinate of the money item
     * @param y the initial Y coordinate of the money item
     */
    public Money(double x, double y) {
        Image image = new Image(getClass().getResourceAsStream("/Image/Money.png"));
        this.entity = new ImageView(image);
        this.entity.setFitWidth(30);
        this.entity.setFitHeight(30);
        this.entity.setTranslateX(x);
        this.entity.setTranslateY(y);
    }

    /**
     * Returns the visual representation of the money item.
     *
     * @return The money item's visual representation
     */
    public ImageView getEntity() {
        return entity;
    }
}
