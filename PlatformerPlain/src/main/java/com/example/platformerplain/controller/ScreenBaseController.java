package com.example.platformerplain.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Provides a base class for screen controllers in the game.
 * <p>
 *     The {@code ScreenBaseController} abstract class serves as a base for other screen
 *     controllers, providing common features such as background
 *     initialisation and monitoring for changes in the global
 *     background colour.
 * </p>
 */
public abstract class ScreenBaseController {

    /**
     * The root pane of the screen.
     */
    @FXML
    protected AnchorPane rootPane;

    /**
     * The image view for displaying the background image.
     */
    @FXML
    protected ImageView backgroundImageView;

    /**
     * Default constructor for the {@code ScreenBaseController}.
     * Initializes a base screen controller with default behavior.
     */
    /*public ScreenBaseController() {
        // Default constructor
    }

     */

    /**
     * Initialises the screen controller.
     * <p>
     *     The {@code initialise} method is used to initialise the screen controller.
     *     <br><br>
     *     It sets up the background using the {@code BackgroundManager},
     *     specifies the initial background colour, and provides a listener to
     *     update the background colour when it changes globally.
     * </p>
     */
    @FXML
    public void initialize() {
        BackgroundManager backgroundManager = BackgroundManager.getInstance();
        backgroundManager.setupBackground(rootPane, backgroundImageView);

        // Set the initial background color
        backgroundManager.updateBackgroundColor(rootPane, backgroundManager.getBackgroundColor());

        // Listen for changes to the global background color
        backgroundManager.backgroundColorProperty().addListener((obs, oldColor, newColor) -> {
            backgroundManager.updateBackgroundColor(rootPane, newColor);
        });
    }
}
