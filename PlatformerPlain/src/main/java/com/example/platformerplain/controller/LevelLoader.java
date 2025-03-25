package com.example.platformerplain.controller;

import com.example.platformerplain.model.LevelModel;
import com.example.platformerplain.model.Money;
import com.example.platformerplain.model.Snake;
import com.example.platformerplain.view.GameView;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Loads and initialises game levels and their components.
 * <p>
 *     The {@code LevelLoader} class is in charge of setting up the game levels,
 *     which include loading backgrounds, platforms, endpoints,
 *     and other elements.
 *     <br><br>
 *     It uses the {@code LevelModel} to retrieve level
 *     data and the {@code MoneyController} to manage the game's money items.
 * </p>
 */
public class LevelLoader {


    /**
     * The file path to the background image.
     */
    private static final String BACKGROUND_IMAGE_PATH = "/Image/background.png";

    /**
     * The number of background images to create.
     */
    private static final int BACKGROUND_IMAGE_COUNT = 5;

    /**
     * Represents the game view for rendering.
     */
    private final GameView view;

    /**
     * Represents the level model containing level data.
     */
    private final LevelModel levelModel;

    /**
     * Controls the collection and management of money items in the game.
     */
    private final MoneyController moneyController;

    /**
     * Initialises the level loader with the given parameters - view, levelModel and moneyController.
     *
     * @param view the game view for rendering
     * @param levelModel the level model containing level data
     * @param moneyController the controller for managing money items
     */
    public LevelLoader(GameView view, LevelModel levelModel, MoneyController moneyController) {
        this.view = view;
        this.levelModel = levelModel;
        this.moneyController = moneyController;
        setupBackgrounds();

        // Use BackgroundManager to update and listen for background color changes
        BackgroundManager backgroundManager = BackgroundManager.getInstance();
        backgroundManager.updateBackgroundColor(view.getGameRoot(), backgroundManager.getBackgroundColor());

        // Add a listener to dynamically update the background color whenever it changes
        backgroundManager.backgroundColorProperty().addListener((obs, oldColor, newColor) -> {
            // Update the background color of the game root to reflect the new color
            backgroundManager.updateBackgroundColor(view.getGameRoot(), newColor);
        });
    }

    /**
     * Sets up the background images for the game.
     */
    private void setupBackgrounds() {
        List<ImageView> backgroundImages = createBackgroundImages(BACKGROUND_IMAGE_PATH, BACKGROUND_IMAGE_COUNT);
        addBackgroundImagesToView(backgroundImages);
        configureBackgroundLayout(backgroundImages);
    }

    /**
     * Adds the background images to the game view.
     *
     * @param backgroundImages the list of background images to add
     */
    private void addBackgroundImagesToView(List<ImageView> backgroundImages) {
        view.getGameRoot().getChildren().addAll(backgroundImages);
    }

    /**
     * Creates the background images for the game.
     *
     * @param imagePath the path to the background image file
     * @param count the number of background images to create
     *
     * @return A list of background images
     */
    private List<ImageView> createBackgroundImages(String imagePath, int count) {
        Image backgroundImage = new Image(getClass().getResourceAsStream(imagePath));
        //Create and return a list of ImageView objects, each displaying the loaded background image
        return List.of(new ImageView(backgroundImage),
                new ImageView(backgroundImage),
                new ImageView(backgroundImage),
                new ImageView(backgroundImage),
                new ImageView(backgroundImage));
    }

    /**
     * Configures the layout properties of the background images.
     *
     * @param backgroundImages the list of background images to configure
     */
    private void configureBackgroundLayout(List<ImageView> backgroundImages) {
        setPreserveRatio(backgroundImages);
        addScenePropertyListener(backgroundImages);
    }

    /**
     * Sets the preserve ratio property of the background images to false.
     *
     * @param backgroundImages the list of background images
     */
    private void setPreserveRatio(List<ImageView> backgroundImages) {
        for (ImageView imageView : backgroundImages) {
            imageView.setPreserveRatio(false);
        }
    }

    /**
     * Adds a listener for the scene property of the game root.
     *
     * @param backgroundImages the list of background images
     */
    private void addScenePropertyListener(List<ImageView> backgroundImages) {
        // Add a listener to monitor changes to the scene property of the game root
        view.getGameRoot().sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Add a listener to the scene's width property to handle background updates
                addWidthPropertyListener(newScene, backgroundImages);
                // Add a listener to the scene's height property to handle background updates
                addHeightPropertyListener(newScene, backgroundImages);
            }
        });
    }

    /**
     * Adds a listener for the width property of the scene.
     *
     * @param newScene the new scene
     * @param backgroundImages the list of background images
     */
    private void addWidthPropertyListener(Scene newScene, List<ImageView> backgroundImages) {
        // Add a listener to monitor changes in the width of the new scene
        newScene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            // Adjust the background images' width whenever the scene's width changes
            configureBackgroundWidth(backgroundImages, newWidth.doubleValue());
        });
    }

    /**
     * Adds a listener for the height property of the scene.
     *
     * @param newScene the new scene
     * @param backgroundImages the list of background images
     */
    private void addHeightPropertyListener(Scene newScene, List<ImageView> backgroundImages) {
        // Add a listener to monitor changes in the height of the new scene
        newScene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            // Adjust the background images' height whenever the scene's height changes
            configureBackgroundHeight(backgroundImages, newHeight.doubleValue());
        });
    }

    /**
     * Configures the width of the background images based on the scene width.
     *
     * @param backgroundImages the list of background images
     * @param width the width of the scene
     */
    private void configureBackgroundWidth(List<ImageView> backgroundImages, double width) {
        // Iterate through each ImageView in the list of background images
        for (int i = 0; i < backgroundImages.size(); i++) {
            ImageView imageView = backgroundImages.get(i);
            // Set the width of the ImageView to the specified width
            imageView.setFitWidth(width);
            // Position the ImageView horizontally by adjusting its X-coordinate
            imageView.setLayoutX(width * i);
        }
    }

    /**
     * Configures the height of the background images based on the scene height.
     *
     * @param backgroundImages the list of background images
     * @param height the height of the scene
     */
    private void configureBackgroundHeight(List<ImageView> backgroundImages, double height) {
        // Iterate through each ImageView in the list of background images
        for (ImageView imageView : backgroundImages) {
            imageView.setFitHeight(height);
        }
    }

    /**
     * Loads the specified level data and initialises the level entities.
     * <p>
     *     The loadLevel method loads the level data from the supplied
     *     array and adds all level entities, such as platforms,
     *     endpoints, money items, eagle and snakes to the game screen.
     * </p>
     *
     * @param levelData the data of the level to load
     */
    public void loadLevel(String[] levelData) {
        levelModel.loadLevel(levelData);
        addAllEntitiesToView();
    }

    /**
     * Adds all the level entities to the game view.
     */
    private void addAllEntitiesToView() {
        addEntitiesToView(levelModel.getPlatforms());
        addEntitiesToView(levelModel.getEndpoints());
        addEntitiesToView(moneyController.getMoneyItems().stream().map(Money::getEntity).collect(Collectors.toList()));
        addEntitiesToView(levelModel.getSnakes().stream().map(Snake::getEntity).collect(Collectors.toList()));
    }

    /**
     * Adds the specified entities to the game view.
     *
     * @param entities the list of entities to add
     */
    private void addEntitiesToView(List<? extends Node> entities) {
        view.getGameRoot().getChildren().addAll(entities);
    }

    /**
     * Returns the list of level endpoints.
     * <p>
     *     The getEndpoints method returns the list of endpoints
     *     at the current level. Endpoints are used to signify
     *     the level's goal or finish line.
     * </p>
     *
     * @return The list of level endpoints
     */
    public List<Node> getEndpoints() {
        return levelModel.getEndpoints();
    }

    /**
     * Returns the list of snakes in the level.
     * <p>
     *     The {@code getSnakes} method returns the list of snake opponents at the current level.
     * </p>
     *
     * @return The list of snakes
     */
    public List<Snake> getSnakes() {
        return levelModel.getSnakes();
    }
}
