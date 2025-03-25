package com.example.platformerplain.model;

import com.example.platformerplain.controller.MoneyController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model for a game level.
 * <p>
 *     The {@code LevelModel} class is responsible for loading
 *     and initialising the game level, which includes platforms,
 *     endpoints, money items, and snakes.
 *     <br><br>
 *     It also manages the collection of these elements within the level.
 * </p>
 */
public class LevelModel {

    /**
     * The width of each platform in the game.
     */
    private static final double PLATFORM_WIDTH = 50;

    /**
     * The height of each platform in the game.
     */
    private static final double PLATFORM_HEIGHT = 50;

    /**
     * The image used for rendering platforms.
     */
    private final Image platformImage;

    /**
     * The image used for rendering endpoints.
     */
    private final Image flagImage;

    /**
     * The list of platform nodes in the level.
     */
    private final List<Node> platforms = new ArrayList<>();

    /**
     * The list of endpoint nodes in the level.
     */
    private final List<Node> endpoints = new ArrayList<>();

    /**
     * The list of snakes in the level.
     */
    private final List<Snake> snakes = new ArrayList<>();

    /**
     * Flag indicating whether an endpoint has been added.
     */
    private boolean endpointAdded = false;

    /**
     * The controller for managing money items in the level.
     */
    private final MoneyController moneyController;

    /**
     * The {@code LevelModel} class constructs a new {@code LevelModel} with
     * the default images for platforms and endpoints, then
     * initialises the money controller.
     */
    public LevelModel() {
        this.platformImage = new Image(getClass().getResourceAsStream("/Image/platform.png"));
        this.flagImage = new Image(getClass().getResourceAsStream("/Image/chickenhouse.png"));
        this.moneyController = new MoneyController(new ArrayList<>(), null);
    }

    /**
     * Loads the specified level data and initializes the level elements.
     *
     * @param levelData the array of strings representing the level layout
     */
    public void loadLevel(String[] levelData) {
        clearLevelData();

        // Iterate through each row of level data
        for (int i = 0; i < levelData.length; i++) {
            String line = levelData[i];
            // Iterate through each column in the line
            for (int j = 0; j < line.length(); j++) {
                char tile = line.charAt(j);

                // Calculate the x and y positions for the tile based on its column and row
                double x = j * PLATFORM_WIDTH;
                double y = i * PLATFORM_HEIGHT;
                processTile(tile, x, y);
            }
        }
    }

    /**
     * Clears the current level data.
     */
    private void clearLevelData() {
        platforms.clear();
        endpoints.clear();
        moneyController.getMoneyItems().clear();
        snakes.clear();
        endpointAdded = false;
    }

    /**
     * Processes a single tile in the level data.
     *
     * @param tile the character representing the element to add
     * @param x the X coordinate for the element
     * @param y the Y coordinate for the element
     */
    private void processTile(char tile, double x, double y) {
        // Use a switch statement to determine what action to take based on the tile type
        switch (tile) {
            // Tile represents a platform
            case '1':
                addPlatform(x, y);
                break;

            // Tile represents an endpoint
            case 'E':
                if (!endpointAdded) {
                    addEndpoint(x, y);
                    endpointAdded = true;
                }
                break;

            // Tile represents money
            case 'M':
                moneyController.addMoney(x, y);
                break;

            // Tile represents snake
            case 'S':
                addSnake(x, y);
                break;
            default:
        }
    }

    /**
     * Adds a platform to the level at the specified coordinates.
     *
     * @param x the X coordinate for the platform
     * @param y the Y coordinate for the platform
     */
    private void addPlatform(double x, double y) {
        ImageView platform = new ImageView(platformImage);
        platform.setFitWidth(PLATFORM_WIDTH);
        platform.setFitHeight(PLATFORM_HEIGHT);
        platform.setTranslateX(x);
        platform.setTranslateY(y);
        platforms.add(platform);
    }

    /**
     * Adds an endpoint to the level at the specified coordinates.
     *
     * @param x the X coordinate for the endpoint
     * @param y the Y coordinate for the endpoint
     */
    private void addEndpoint(double x, double y) {
        ImageView endpoint = new ImageView(flagImage);
        endpoint.setFitWidth(PLATFORM_WIDTH);
        endpoint.setFitHeight(PLATFORM_HEIGHT);
        endpoint.setTranslateX(x);
        endpoint.setTranslateY(y);
        endpoints.add(endpoint);
    }

    /**
     * Adds a snake to the level at the specified coordinates.
     *
     * @param x the X coordinate for the snake
     * @param y the Y coordinate for the snake
     */
    private void addSnake(double x, double y) {
        Snake snake = new Snake(x, y);
        snakes.add(snake);
    }

    /**
     * Returns the list of platforms in the level.
     *
     * @return The list of platforms
     */
    public List<Node> getPlatforms() {
        return platforms;
    }

    /**
     * Returns the list of endpoints in the level.
     *
     * @return The list of endpoints
     */
    public List<Node> getEndpoints() {
        return endpoints;
    }

    /**
     * Returns the list of money items in the level.
     *
     * @return The list of money items
     */
    public List<Money> getMoneyItems() {
        return moneyController.getMoneyItems();
    }

    /**
     * Returns the list of snakes in the level.
     *
     * @return The list of snakes
     */
    public List<Snake> getSnakes() {
        return snakes;
    }

    /**
     * Returns the money controller used by this level model.
     *
     * @return The money controller
     */
    public MoneyController getMoneyController() {
        return moneyController;
    }
}
