package com.example.platformerplain.model;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Player class.
 * This class contains unit tests for the Player class's methods and properties.
 */
public class PlayerTest {
    private Player player;

    /**
     * Sets up a new Player instance before each test.
     */
    @BeforeEach
    public void setUp() {
        player = new Player(0, 500, 70, 70, "/Image/Chicken.png");
    }

    /**
     * Tests that the player entity is initialized correctly.
     */
    @Test
    public void testPlayerInitialization() {
        ImageView entity = player.getEntity();
        assertNotNull(entity);
        assertEquals(70, entity.getFitWidth());
        assertEquals(70, entity.getFitHeight());
        assertEquals(0, entity.getTranslateX());
        assertEquals(500, entity.getTranslateY());
    }

    /**
     * Tests that the player's velocity can be set and retrieved correctly.
     */
    @Test
    public void testSetAndGetVelocity() {
        Point2D velocity = new Point2D(5, 10);
        player.setVelocity(velocity);
        assertEquals(velocity, player.getVelocity());
    }

    /**
     * Tests that the player's ability to jump can be set and retrieved correctly.
     */
    @Test
    public void testSetAndGetCanJump() {
        player.setCanJump(false);
        assertFalse(player.canJump());
        player.setCanJump(true);
        assertTrue(player.canJump());
    }

    /**
     * Tests that the player's movement state is detected correctly.
     */
    @Test
    public void testIsMoving() {
        assertFalse(player.isMoving()); // Initially not moving

        // Simulate movement by changing the position
        player.getEntity().setTranslateX(10);
        player.getEntity().setTranslateY(10);
        assertTrue(player.isMoving());

        // No further movement
        assertFalse(player.isMoving());
    }
}
