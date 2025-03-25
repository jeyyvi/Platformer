package com.example.platformerplain.controller;

import com.example.platformerplain.model.Eagle;
import com.example.platformerplain.model.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.List;

/**
 * Manages the behavior and interactions of eagle, such as movements.
 * <p>
 *     The {@code EagleController} is a class that manages the behaviour and
 *     interactions of the eagle within the game.
 *     <br><br>
 *     The controller interacts with the game to ensure the eagle moves
 *     correctly towards the direction of the player, which is represented by a chicken.
 *     <br><br>
 *     The player will lose the game once the eagle collides with the chicken.
 * </p>
 * @see com.example.platformerplain.model.Eagle
 */

public class EagleController {

    /**
     * The Eagle entity managed by the controller.
     * <p>
     *     A single Eagle instance that the controller will control, such as
     *     the movement and interactions within the game environment.
     * </p>
     */

    private final Eagle eagle;

    /**
     * Controls the movement speed of the Eagle entity.
     * <p>
     *     Represents the speed at which the Eagle moves
     *     within the game.
     * </p>
     */

    private final double speed;

    /**
     * Holds a list of Node objects representing the platforms within the game environment.
     * <p>
     *     All the platforms that are in the game are tracked by the platforms field,
     *     which allows the game logic to manage interactions with them.
     * </p>
     * @see javafx.scene.Node
     */

    private final List<Node> platforms;

    /**
     * Represents the player entity within the game.
     * <p>
     *     Handles the player's actions, movements, and interactions within the game environment.
     * </p>
     * @see com.example.platformerplain.model.Player
     */

    private final Player player;

    /**
     * The Game State managed by this controller.
     * <p>
     *     Handles transitions between different game states such as starting or ending the game and also ensures the
     *     game's state is consistently maintained throughout gameplay.
     * </p>
     *@see com.example.platformerplain.controller.GameStateController
     */

    private final GameStateController gameStateManager;

    /**
     * Indicates whether the grace period is currently active.
     * <p>
     *     The boolean variable gracePeriodActive is initialized to true by default,
     *     indicating that grace period is active at the start.
     * </p>
     */

    private boolean gracePeriodActive = true;

    /**
     * Manages the delay before the eagle performs an action in the game.
     */

    private Timeline eagleDelay;

    /**
     * Manages the duration of the grace period in the game.
     * @see javafx.animation.Timeline
     * @see javafx.util.Duration
     */

    private Timeline gracePeriodTimeline;


    /**
     * Initialises the {@code EagleController} instance with 5 parameters - eagle, platforms, speed player and gameStateManager.
     *
     * @param eagle the Eagle entity that is managed by this controller
     * @param platforms a list of platform nodes in the game environment
     * @param speed the flying speed of the eagle
     * @param player player entity that is managed by this controller
     * @param gameStateManager the controller that manages the game's state
     */

    public EagleController(Eagle eagle, List<Node> platforms, double speed, Player player, GameStateController gameStateManager) {
        this.eagle = eagle;
        this.platforms = platforms;
        this.speed = speed;
        this.player = player;
        this.gameStateManager = gameStateManager;
        initializeEagle();
    }

    /**
     * Initialises the Eagle entity within the game.
     * <p>
     *     This method sets up the initial state and properties of the Eagle entity.
     * </p>
     */

    private void initializeEagle() {
        eagle.getEntity().setTranslateX(player.getEntity().getTranslateX());
        eagle.getEntity().setTranslateY(player.getEntity().getTranslateY() - 200);
        initializeEagleDelay();
        initializeGracePeriod();
    }

    /**
     * Initialises the delay mechanism for the eagle's actions.
     * <p>
     *     This method sets up the 'Timeline' object that manages
     *     the delay before the eagle takes an action.
     * </p>
     */

    // Initialises the eagle delay time
    private void initializeEagleDelay() {
        eagleDelay = new Timeline(new KeyFrame(Duration.seconds(3), event -> startEagleMovement()));
        eagleDelay.setCycleCount(1);
        eagleDelay.play();
    }

    /**
     * Initialises the grace period mechanism.
     */

    // Initialise the grace period of the eagle
    private void initializeGracePeriod() {
        gracePeriodTimeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> gracePeriodActive = false));
        gracePeriodTimeline.setCycleCount(1);
        gracePeriodTimeline.play();
    }

    /**
     * Starts the eagle's movement in the game.
     * <p>
     *     The startEagleMovement method ensures that the eagle
     *     begins moving following the defined speed and behavior patterns.
     *     <br><br>
     * </p>
     */

    private void startEagleMovement() {
        AnimationTimer eagleTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateMovement(player.getEntity().getTranslateX(), player.getEntity().getTranslateY());
            }
        };
        eagleTimer.start();
    }

    /**
     * Adjusts the eagle's position to move it towards to the specified target coordinates (targetX, targetY).
     *
     * @param targetX the X coordinate of the target position
     * @param targetY the Y coordinate of the target position
     */

    // To update the movement of the eagle
    public void updateMovement(double targetX, double targetY) {
        // Calculate the direction vector from the current position to the target position
        Point2D direction = calculateDirection(targetX, targetY);
        if (direction.magnitude() > 0) {
            // Set the eagle's velocity based on the calculated direction
            eagle.setVelocity(calculateVelocity(direction));
            // Move the eagle by updating its position using the calculated velocity
            moveEagle((int) eagle.getVelocity().getX(), (int) eagle.getVelocity().getY());
        }
    }

    /**
     * Calculates the path between the eagle's current position to the specified target coordinates.
     *
     * @param targetX the X coordinate of the target position
     * @param targetY the Y coordinate of the target position
     * @return A 'Point2D' object representing the normalized direction vector
     */

    private Point2D calculateDirection(double targetX, double targetY) {
        double dx = targetX - eagle.getEntity().getTranslateX();
        double dy = targetY - eagle.getEntity().getTranslateY();
        return new Point2D(dx, dy);
    }

    /**
     * Calculates the eagle's speed based on the given direction vector.
     *
     * @param direction a 'Point2D' object representing the direction vector
     * @return A 'Point2D' object representing the speed vector
     */

    private Point2D calculateVelocity(Point2D direction) {
        double distance = direction.magnitude();
        return new Point2D(speed * direction.getX() / distance, speed * direction.getY() / distance);
    }

    /**
     * Moves the eagle by the specified distances along the X and Y axes.
     *
     * @param dx the distance to move the eagle along the X axis
     * @param dy the distance to move the eagle along the Y axis
     */

    private void moveEagle(int dx, int dy) {
        eagle.moveX(dx);
        eagle.moveY(dy);
    }

    /**
     * Checks for collisions between the eagle and the player(chicken).
     *
     * @param controller the {@code GameController} instance used to manage game objects and check for collisions
     */

    public void checkEagleCollision(GameController controller) {
        // Check if the grace period is not active and the player collides with the eagle
        if (!gracePeriodActive && player.getEntity().getBoundsInParent().intersects(eagle.getEntity().getBoundsInParent())) {
            gameStateManager.setState(gameStateManager.getGameEndState());
            gameStateManager.update(controller);  // Trigger game end state to end the game
        }
    }

    /**
     * Returns the Eagle entity managed by this controller.
     * <p>
     *     The getEagle method provides access to the Eagle
     *     entity that is being managed by the {@code EagleController}.
     * </p>
     * @return The Eagle entity managed by this controller
     */

    public Eagle getEagle() {
        return eagle;
    }

    /**
     * Sets the grace period status to the specified value.
     *
     * @param gracePeriodActive a boolean value to set the grace period status
     */

    public void setGracePeriodActive(boolean gracePeriodActive) {
        this.gracePeriodActive = gracePeriodActive;
    }
}
