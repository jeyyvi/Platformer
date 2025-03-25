package com.example.platformerplain.controller;

import com.example.platformerplain.model.Player;
import com.example.platformerplain.observer.ScoreModel;
import com.example.platformerplain.view.GameView;
import com.example.platformerplain.observer.ScoreView;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the GamePhysics class.
 *
 * It verifies the following functionalities:
 * - Applying gravity to the player.
 * - Collision detection on X and Y axes.
 *
 * @see GamePhysics
 */
public class GamePhysicsTest {
    private GamePhysics gamePhysics;
    private Player player;
    private List<Node> platforms;
    private GameView gameView;
    private ScoreController scoreController;

    @BeforeEach
    public void setUp() {
        platforms = new ArrayList<>();
        gamePhysics = new GamePhysics(platforms);
        player = new Player(0, 500, 70, 70, "/Image/Chicken.png");
        gameView = new GameView();
        scoreController = new ScoreController(new ScoreModel(), new ScoreView(new Pane()), player, new MoneyController(new ArrayList<>(), player.getEntity()));
    }

    /**
     * Tests that gravity is applied correctly to the player.
     */
    @Test
    public void testApplyGravity() {
        player.setVelocity(new Point2D(0, 0)); // Initial velocity
        gamePhysics.applyGravity(player);
        assertEquals(new Point2D(0, 1), player.getVelocity(), "Player's velocity should increase by 1 due to gravity.");
    }

    /**
     * Tests collision detection on the X-axis.
     */
    @Test
    public void testCheckCollisionX() {
        Node platform = new ImageView();
        platform.setTranslateX(5);
        platform.setTranslateY(0);
        platforms.add(platform);

        assertFalse(gamePhysics.checkCollisionX(player, true), "No collision should be detected when moving right with no platform.");
    }

    /**
     * Tests collision detection on the Y-axis.
     */
    @Test
    public void testCheckCollisionY() {
        Node platform = new ImageView();
        platform.setTranslateX(0);
        platform.setTranslateY(5);
        platforms.add(platform);

        assertFalse(gamePhysics.checkCollisionY(player, true), "No collision should be detected when moving down with no platform.");
    }

    /**
     * Tests that no collision is detected when there are no platforms.
     */
    @Test
    public void testNoCollision() {
        assertFalse(gamePhysics.checkCollisionX(player, true), "No collision should be detected on X-axis with no platforms.");
        assertFalse(gamePhysics.checkCollisionY(player, true), "No collision should be detected on Y-axis with no platforms.");
    }
}
