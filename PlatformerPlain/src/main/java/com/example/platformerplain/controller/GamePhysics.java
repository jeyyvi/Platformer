package com.example.platformerplain.controller;

import com.example.platformerplain.model.Fireball;
import com.example.platformerplain.model.Player;
import com.example.platformerplain.model.Snake;
import com.example.platformerplain.view.GameView;
import javafx.scene.Node;

import java.util.Iterator;
import java.util.List;


/**
 * Manages the physics interactions and collision detection in the game.
 * <p>
 *     The {@code GamePhysics} class handles the application of gravity to the player, checks
 *     for collisions between the player and platforms, and manages interactions
 *     between the player and other game objects such as snakes and fireballs.
 * </p>
 */
public class GamePhysics {

    /**
     * The list of platforms in the game.
     */
    private List<Node> platforms;

    /**
     * The maximum limit for gravity's effect on the player.
     */
    private static final int GRAVITY_LIMIT = 10;

    /**
     * The acceleration due to gravity.
     */
    private static final int GRAVITY_ACCELERATION = 1;

    /**
     * The width of the player character.
     */
    private static final int PLAYER_WIDTH = 70;

    /**
     * The height of the player character.
     */
    private static final int PLAYER_HEIGHT = 70;

    /**
     * The width of the platform.
     */
    private static final int PLATFORM_WIDTH = 50;

    /**
     * The height of the platform.
     */
    private static final int PLATFORM_HEIGHT = 50;

    /**
     * Constructs a new {@code GamePhysics} with the specified list of platforms.
     *
     * @param platforms the list of platforms in the game
     */
    public GamePhysics(List<Node> platforms) {
        this.platforms = platforms;
    }

    /**
     * Applies gravity to the player, adjusting their vertical velocity.
     *
     * @param player the player character
     */
    public void applyGravity(Player player) {
        if (player.getVelocity().getY() < GRAVITY_LIMIT) {
            player.setVelocity(player.getVelocity().add(0, GRAVITY_ACCELERATION));
        }
    }

    /**
     * Checks for horizontal collisions between the player and platforms.
     *
     * @param player the player character
     * @param movingRight true if the player is moving right, false if moving left
     *
     * @return True if a collision is detected, false if it is not
     */
    public boolean checkCollisionX(Player player, boolean movingRight) {
        double futureX = player.getEntity().getTranslateX() + (movingRight ? 1 : -1);
        for (Node platform : platforms) {
            if (isCollision(futureX, platform.getTranslateX(), PLAYER_WIDTH, PLATFORM_WIDTH,
                    player.getEntity().getTranslateY(), platform.getTranslateY(), PLAYER_HEIGHT, PLATFORM_HEIGHT)) {
                return true; // Collision detected
            }
        }
        return false; // No collision
    }

    /**
     * Checks for vertical collisions between the player and platforms.
     *
     * @param player the player character
     * @param movingDown true if the player is moving down, false if moving up
     *
     * @return True if a collision is detected, false if it is not
     */
    public boolean checkCollisionY(Player player, boolean movingDown) {
        double futureY = player.getEntity().getTranslateY() + (movingDown ? 1 : -1);
        for (Node platform : platforms) {
            if (isCollision(player.getEntity().getTranslateX(), platform.getTranslateX(), PLAYER_WIDTH, PLATFORM_WIDTH,
                    futureY, platform.getTranslateY(), PLAYER_HEIGHT, PLATFORM_HEIGHT)) {
                return true; // Collision detected
            }
        }
        return false; // No collision
    }

    /**
     * Determines if a collision occurs between the player and a platform.
     *
     * @param playerX the player's X coordinate
     * @param platformX the platform's X coordinate
     * @param playerWidth the width of the player
     * @param platformWidth the width of the platform
     * @param playerY the player's Y coordinate
     * @param platformY the platform's Y coordinate
     * @param playerHeight the height of the player
     * @param platformHeight the height of the platform
     *
     * @return True if a collision is detected, false if it is not
     */
    private boolean isCollision(double playerX, double platformX, int playerWidth, int platformWidth,
                                double playerY, double platformY, int playerHeight, int platformHeight) {
        return playerX + playerWidth > platformX &&
                playerX < platformX + platformWidth &&
                playerY < platformY + platformHeight &&
                playerY + playerHeight > platformY;
    }

    /**
     * Checks for collisions between the player and snakes.
     *
     * @param controller the game controller managing game state
     */
    public void checkSnakeCollision(GameController controller) {
        // Retrieve the list of snakes, the player, the game view, and the score controller from the game controller
        List<Snake> snakes = controller.getSnakes();
        Player player = controller.getPlayer();
        GameView view = controller.getView();
        ScoreController scoreController = controller.getScoreController();

        // Iterate over the list of snakes to check for collisions with the player
        Iterator<Snake> iterator = snakes.iterator();
        while (iterator.hasNext()) {
            Snake snake = iterator.next();
            // Check if the current snake has collided with the player
            if (isSnakeCollision(player, snake)) {
                handleSnakeCollision(view, iterator, scoreController, snake);
                break;  // Exit after handling the collision
            }
        }
    }

    /**
     * Determines if a collision occurs between the player and a snake.
     *
     * @param player the player character
     * @param snake the snake character
     *
     * @return True if a collision is detected, false otherwise
     */
    private boolean isSnakeCollision(Player player, Snake snake) {
        return player.getEntity().getBoundsInParent().intersects(snake.getEntity().getBoundsInParent());
    }

    /**
     * Handles the collision between the player and a snake.
     *
     * @param view the game view
     * @param iterator the iterator for the snake list
     * @param scoreController the score controller
     * @param snake the snake character
     */
    private void handleSnakeCollision(GameView view, Iterator<Snake> iterator, ScoreController scoreController, Snake snake) {
        scoreController.handleSnakeCollision();
        view.getGameRoot().getChildren().remove(snake.getEntity());
        iterator.remove();
        System.out.println("Snake removed from game.");
    }

    /**
     * Updates the positions and checks for collisions of fireballs.
     *
     * @param controller the game controller managing game state
     */
    public void updateFireballs(GameController controller) {
        // Retrieve the fireball controller, fireball list, game view, and list of snakes
        FireballController fireballController = controller.getFireballController();
        List<Fireball> fireballs = fireballController.getFireballs();
        GameView view = controller.getView();
        List<Snake> snakes = controller.getSnakes();

        // Iterate over all fireballs in the game
        Iterator<Fireball> fireballIterator = fireballs.iterator();
        while (fireballIterator.hasNext()) {
            Fireball fireball = fireballIterator.next();
            // Update the fireball's position based on its velocity and direction
            fireball.updatePosition();
            checkFireballCollisions(fireball, fireballIterator, snakes, view);
        }
    }

    /**
     * Checks for collisions between fireballs and snakes or the boundaries of the game view.
     *
     * @param fireball the fireball to check
     * @param fireballIterator the iterator for the fireball list
     * @param snakes the list of snakes
     * @param view the game view
     */
    private void checkFireballCollisions(Fireball fireball, Iterator<Fireball> fireballIterator, List<Snake> snakes, GameView view) {
        // Iterate through the list of snakes to check for collisions with the fireball
        Iterator<Snake> snakeIterator = snakes.iterator();
        while (snakeIterator.hasNext()) {
            Snake snake = snakeIterator.next();
            // If a collision is detected between the fireball and a snake
            if (isFireballCollision(fireball, snake)) {
                handleFireballCollision(view, fireballIterator, snakeIterator, fireball, snake);
                break;
            }
        }
        // Check if the fireball has gone out of bounds
        if (isFireballOutOfBounds(fireball, view)) {
            handleFireballOutOfBounds(view, fireballIterator, fireball);
        }
    }

    /**
     * Determines if a collision occurs between a fireball and a snake.
     *
     * @param fireball the fireball
     * @param snake the snake
     *
     * @return True if a collision is detected, false if it is not
     */
    private boolean isFireballCollision(Fireball fireball, Snake snake) {
        return fireball.getEntity().getBoundsInParent().intersects(snake.getEntity().getBoundsInParent());
    }

    /**
     * Handles the collision between a fireball and a snake.
     *
     * @param view the game view
     * @param fireballIterator the iterator for the fireball list
     * @param snakeIterator the iterator for the snake list
     * @param fireball the fireball
     * @param snake the snake
     */
    private void handleFireballCollision(GameView view, Iterator<Fireball> fireballIterator, Iterator<Snake> snakeIterator, Fireball fireball, Snake snake) {
        view.getGameRoot().getChildren().remove(snake.getEntity());
        view.getGameRoot().getChildren().remove(fireball.getEntity());
        fireballIterator.remove();
        snakeIterator.remove();
    }

    /**
     * Determines if a fireball is out of bounds of the game view.
     *
     * @param fireball the fireball
     * @param view the game view
     * @return True if the fireball is out of bounds, false if it is not
     */
    private boolean isFireballOutOfBounds(Fireball fireball, GameView view) {
        return fireball.getEntity().getTranslateX() > view.getGameRoot().getWidth();
    }

    /**
     * Handles a fireball that is out of bounds.
     *
     * @param view the game view
     * @param fireballIterator the iterator for the fireball list
     * @param fireball the game view
     */
    private void handleFireballOutOfBounds(GameView view, Iterator<Fireball> fireballIterator, Fireball fireball) {
        view.getGameRoot().getChildren().remove(fireball.getEntity());
        fireballIterator.remove();
    }
}