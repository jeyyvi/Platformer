package com.example.platformerplain.controller;

import com.example.platformerplain.model.Money;
import com.example.platformerplain.model.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the MoneyController class.
 *
 * It verifies the following functionalities:
 * - Adding money items to the controller.
 * - Collecting money items by the player.
 *
 * @see MoneyController
 */
public class MoneyControllerTest {
    private MoneyController moneyController;
    private Player player;
    private List<Money> moneyItems;

    /**
     * Sets up the test environment before each test.
     * Initializes the Player, MoneyController, and list of money items.
     */
    @BeforeEach
    public void setUp() {
        player = new Player(0, 0, 70, 70, "/Image/Chicken.png");
        moneyItems = new ArrayList<>();
        moneyController = new MoneyController(moneyItems, player.getEntity());
    }

    /**
     * Tests that money items are correctly added to the controller.
     */
    @Test
    public void testAddMoney() {
        moneyController.addMoney(100, 100);
        assertEquals(1, moneyItems.size());
        Money money = moneyItems.get(0);

        assertEquals(100, money.getEntity().getTranslateX(), "Money X position should be 100.");
        assertEquals(100, money.getEntity().getTranslateY(), "Money Y position should be 100.");
    }

    /**
     * Tests that the player can collect money items and that they are removed from the controller.
     */
    @Test
    public void testCollectMoney() {
        Money money = new Money(0, 0);
        moneyItems.add(money);
        Pane parent = new Pane();
        parent.getChildren().add(money.getEntity());

        // Ensure money is collected
        int collected = moneyController.collectMoney(player);

        assertTrue(moneyItems.isEmpty(), "Money items list should be empty after collection.");
        assertFalse(parent.getChildren().contains(money.getEntity()), "Parent should not contain money entity after collection.");
        assertEquals(1, collected, "Collected count should be 1.");
    }
}
