package com.example.platformerplain.controller;

import com.example.platformerplain.model.Player;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the PlayerController class.
 *
 * It verifies the following functionalities:
 * - Player movement along the X-axis.
 * - Player's ability to jump.
 *
 * @see PlayerController
 */
public class PlayerControllerTest {
    private Player player;
    private PlayerController playerController;
    private GamePhysics gamePhysics;

    /**
     * Sets up the test environment before each test.
     * Initializes the Player, PlayerController, and mock GamePhysics.
     */
    @BeforeEach
    public void setUp() {
        player = new Player(0, 500, 70, 70, "/Image/Chicken.png");

        // Create a list of mock platforms
        List<Node> platforms = new ArrayList<>();
        platforms.add(new ImageView());

        gamePhysics = new GamePhysics(platforms);
        playerController = new PlayerController(player, gamePhysics);
    }

    /**
     * Tests that the player correctly moves along the X-axis.
     */
    @Test
    public void testMoveX() {
        playerController.moveX(5);

        assertEquals(5, player.getEntity().getTranslateX(), "Player X position should be 5 after moving.");
    }

    /**
     * Tests that the player can jump and updates the player's velocity.
     */
    @Test
    public void testJump() {
        player.setCanJump(true);
        playerController.jump();

        assertEquals(new Point2D(0, -30), player.getVelocity(), "Player velocity should be (0, -30) after jumping.");
    }
}
