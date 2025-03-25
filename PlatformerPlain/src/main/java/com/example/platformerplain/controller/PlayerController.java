package com.example.platformerplain.controller;

import com.example.platformerplain.model.Player;

/**
 * Controls the player's movements and interactions with the game environment.
 * <p>
 *     The {@code PlayerController} class controls the player's horizontal
 *     and vertical movements, jumping, and gravity.
 *     <br><br>
 *     It interacts with the {@code GamePhysics} class to detect collisions
 *     and apply physics-based behaviours to the player.
 * </p>
 */
public class PlayerController {

    /**
     * Represents the player character.
     */
    private Player player;

    /**
     * The physics engine for the game.
     */
    private GamePhysics gamePhysics;

    // Constants for player movements

    /**
     * The velocity applied to the player when jumping.
     */
    private static final int JUMP_VELOCITY = 30;

    /**
     * Constructs a new {@code PlayerController} with the specified
     * player and game physics.
     *
     * @param player the player character
     * @param gamePhysics the game physics engine
     */
    public PlayerController(Player player, GamePhysics gamePhysics) {
        this.player = player;
        this.gamePhysics = gamePhysics;
    }

    /**
     * Moves the player horizontally by the specified value.
     * <p>
     *     The {@code moveX} method moves the player to the left or right,
     *     depending on the value given. It checks for collisions along
     *     the way and stops the player's movement if one is detected.
     * </p>
     * @param value the distance to move the player horizontally
     */
    public void moveX(int value) {
        boolean movingRight = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            // Check if the player would collide while moving in the specified direction
            if (!gamePhysics.checkCollisionX(player, movingRight)) {
                // If no collision, move the player by 1 unit in the specified direction
                player.getEntity().setTranslateX(player.getEntity().getTranslateX() + (movingRight ? 1 : -1));
            }
        }
    }

    /**
     * Moves the player vertically by the specified value.
     * <p>
     *     The {@code moveY} method moves the player up or down based
     *     depending on the given value. It checks for collisions along
     *     the way and stops movement if one is detected.
     *     <br><br>
     *     It allows the player to jump again after landing
     *     if the moving down condition is true.
     * </p>
     * @param value the distance to move the player vertically
     */
    public void moveY(int value) {
        boolean movingDown = value > 0;
        for (int i = 0; i < Math.abs(value); i++) {
            // Check if the player would collide while moving in the specified vertical direction
            if (!gamePhysics.checkCollisionY(player, movingDown)) {
                // If no collision, move the player by 1 unit in the specified vertical direction
                player.getEntity().setTranslateY(player.getEntity().getTranslateY() + (movingDown ? 1 : -1));
            } else {
                // If a collision is detected while moving down
                if (movingDown) {
                    player.setCanJump(true); // Allow jump after landing
                }
            }
        }
    }

    /**
     * Makes the player jump.
     * <p>
     *     The {@code jump} method method causes the player to jump
     *     by applying a vertical velocity.
     *     <br><br>
     *     This method makes sure the player can jump and prevents
     *     continuous jumping until the player lands again.
     * </p>
     */
    public void jump() {
        if (player.canJump()) {
            player.setVelocity(player.getVelocity().add(0, -JUMP_VELOCITY));
            player.setCanJump(false); // Prevent continuous jumping
        }
    }

    /**
     * Applies gravity to the player.
     * <p>
     *     The {@code applyGravity} method applies gravity to the player,
     *     making sure the player falls downwards, unless obstructed by
     *     a platform or other object.
     * </p>
     */
    public void applyGravity() {
        gamePhysics.applyGravity(player);
    }
}
