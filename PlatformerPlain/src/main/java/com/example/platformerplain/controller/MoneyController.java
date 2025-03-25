package com.example.platformerplain.controller;

import com.example.platformerplain.model.Money;
import com.example.platformerplain.model.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Iterator;
import java.util.List;

/**
 * Manages the collection and interactions of money items in the game.
 *<p>
 *     This class is responsible for the creation, collection, and
 *     management of money items that the player can obtain
 *     during the game.
 *     <br><br>
 *     It tracks the money items, detects if the player
 *     encounters the money items, and updates the game state accordingly.
 *</p>
 */
public class MoneyController {

    /**
     * The list of money items in the game.
     */
    private final List<Money> moneyItems;

    /**
     * The visual representation of the player.
     */
    private ImageView playerEntity;

    /**
     * Constructs a new {@code MoneyController} with the specified money
     * items and player entity.
     *
     * @param moneyItems the list of money items in the game
     * @param playerEntity the visual representation of the player
     */
    public MoneyController(List<Money> moneyItems, ImageView playerEntity) {
        this.moneyItems = moneyItems;
        this.playerEntity = playerEntity;
    }

    /**
     * Adds a new money item at the specified coordinates.
     * <p>
     *     The {@code addMoney} method creates a new money item at
     *     the specified X and Y coordinates and adds it to the
     *     list of money items.
     * </p>
     *
     * @param x the X coordinate of the new money item
     * @param y the Y coordinate of the new money item
     */
    public void addMoney(double x, double y) {
        Money money = new Money(x, y);
        moneyItems.add(money);
    }

    /**
     * Returns the list of money items.
     *
     * @return The list of money items.
     */
    public List<Money> getMoneyItems() {
        return moneyItems;
    }

    /**
     * Collects money items that the player collides with.
     * <p>
     *     The {@code collectMoney} method Checks for collisions with the
     *     player and money items.
     *     <br><br>
     *     If a collision is detected, the money item is collected
     *     and removed from the game, while the count of collected money
     *     items is increase by 1.
     * </p>
     *
     * @param player the player character
     *
     * @return The number of money items collected
     */
    public int collectMoney(Player player) {
        int collectedCount = 0;
        Iterator<Money> iterator = moneyItems.iterator();
        // Loop through each money item in the collection
        while (iterator.hasNext()) {
            Money money = iterator.next();
            // Check if the player intersects with the current money item
            if (player.getEntity().getBoundsInParent().intersects(money.getEntity().getBoundsInParent())) {
                collectMoney(money);
                iterator.remove();
                collectedCount++;
            }
        }
        // Return the total count of collected money items
        return collectedCount;
    }

    /**
     * Collects a specific money item and removes it from the game view.
     *
     * @param money the money item to collect
     */
    private void collectMoney(Money money) {
        System.out.println("Money collected: " + money);
        // Check if the parent of the money entity is a Pane
        if (money.getEntity().getParent() instanceof Pane) {
            // Remove the money entity from its parent's children
            ((Pane) money.getEntity().getParent()).getChildren().remove(money.getEntity());
        } else {
            System.err.println("Parent is not a Pane or Group. Cannot remove money entity.");
        }
    }

    /**
     * Sets the player entity to be used for collision detection with money items.
     * <p>
     *     The {@code playerEntity} method updates the player entity used to detect
     *     collisions with money items.
     * </p>
     *
     * @param playerEntity the visual representation of the player
     */
    public void setPlayerEntity(ImageView playerEntity) {
        this.playerEntity = playerEntity;
    }
}
