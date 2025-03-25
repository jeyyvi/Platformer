package com.example.platformerplain.controller;

import com.example.platformerplain.model.Fireball;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the creation and management of fireballs.
 * <p>
 *     The {@code FireballController} class provides methods to shoot fireballs,
 *     manage the list of active fireballs, and handle their interactions
 *     within the game.
 *     <br><br>
 *     Each fireball is represented by a 'Fireball' object and is
 *     managed based on its speed and image path.
 * </p>
 */
public class FireballController {
    /**
     * The list of active fireballs in the game.
     * <p>
     *     This field holds a list of `Fireball` objects that
     *     are currently active in the game.
     * </p>
     */
    private List<Fireball> fireballs = new ArrayList<>();

    /**
     * The file path to the fireball image.
     * <p>
     *     This field stores the file path to the image
     *     used for the fireball sprite.
     * </p>
     */
    private String fireImagePath;

    /**
     * The speed at which the fireball moves.
     * <p>
     *     This field defines the speed of the fireball.
     * </p>
     */
    private double fireballSpeed;

    /**
     * Constructs a new {@code FireballController} with the specified image path and speed.
     *
     * @param fireImagePath the file path to the fireball image
     * @param fireballSpeed the fireball's moving speed
     */
    public FireballController(String fireImagePath, double fireballSpeed) {
        this.fireImagePath = fireImagePath;
        this.fireballSpeed = fireballSpeed;
    }

    /**
     * Shoots a new fireball from the player's character(chicken).
     * <p>
     *     The shootFireball method creates a new 'Fireball' object starting
     *     from the given coordinates '(startX, startY)' and adds it to the
     *     list of active fireballs.
     * </p>
     *
     * @param startX the X coordinate of the fireball's starting position
     * @param startY the Y coordinate of the fireball's starting position
     * @return The created 'Fireball' object
     */
    public Fireball shootFireball(double startX, double startY) {
        Fireball fireball = new Fireball(startX, startY, fireballSpeed, fireImagePath);
        fireballs.add(fireball);
        return fireball;
    }

    /**
     * Returns the list of active fireballs.
     *
     * @return The list of active fireballs
     */
    public List<Fireball> getFireballs() {
        return fireballs;
    }

    /**
     * Removes the specified fireball from the list of active fireballs.
     *
     * @param fireball the 'Fireball' object to remove
     */
    public void removeFireball(Fireball fireball) {
        fireballs.remove(fireball);
    }
}
