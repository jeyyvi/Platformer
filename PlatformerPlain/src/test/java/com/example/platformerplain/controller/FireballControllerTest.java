package com.example.platformerplain.controller;

import com.example.platformerplain.model.Fireball;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the FireballController class.
 *
 * It verifies the following functionalities:
 * - Shooting fireballs.
 * - Retrieving the list of active fireballs.
 * - Removing fireballs from the controller.
 *
 * @see FireballController
 */
public class FireballControllerTest {
    private FireballController fireballController;
    private static final String FIRE_IMAGE_PATH = "/Image/fire.png";
    private static final double FIREBALL_SPEED = 10.0;

    /**
     * Sets up the test environment before each test.
     * Initializes the FireballController with a fire image path and speed.
     */
    @BeforeEach
    public void setUp() {
        fireballController = new FireballController(FIRE_IMAGE_PATH, FIREBALL_SPEED);
    }

    /**
     * Tests that a fireball is correctly created and initialized when shot.
     */
    @Test
    public void testShootFireball() {
        Fireball fireball = fireballController.shootFireball(0, 0);
        assertEquals(0, fireball.getEntity().getTranslateX(), "Fireball X position should be 0.");
        assertEquals(0, fireball.getEntity().getTranslateY(), "Fireball Y position should be 0.");
        assertEquals(FIREBALL_SPEED, fireball.getSpeed(), "Fireball speed should be 10.0.");
    }

    /**
     * Tests that the list of active fireballs is correctly retrieved.
     */
    @Test
    public void testGetFireballs() {
        fireballController.shootFireball(0, 0);
        List<Fireball> fireballs = fireballController.getFireballs();
        assertEquals(1, fireballs.size(), "Fireballs list size should be 1.");
    }

    /**
     * Tests that a fireball is correctly removed from the controller.
     */
    @Test
    public void testRemoveFireball() {
        Fireball fireball = fireballController.shootFireball(0, 0);
        fireballController.removeFireball(fireball);
        List<Fireball> fireballs = fireballController.getFireballs();
        assertTrue(fireballs.isEmpty(), "Fireballs list should be empty after removal.");
    }
}
