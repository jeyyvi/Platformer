package com.example.platformerplain.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * The {@code BackgroundManager} class is a singleton that manages the background colour and image of the game.
 *
 * <p>
 *      This class will listen for any changes of the background colour and updates it to ensure a consistent background colour across all game screens.
 *      <br><br>
 *      The default background colour is white.
 * </p>
 *
 * @see javafx.beans.property.ObjectProperty
 * @see javafx.scene.paint.Color
 * @see javafx.scene.layout.AnchorPane
 * @see javafx.scene.image.ImageView
 */

public class BackgroundManager {

    /**
     * The singleton instance of {@code BackgroundManager}.
     * <p>
     *      Holds the single instance of the {@code BackgroundManager} and
     *      ensure that only one instance exists throughout the runtime of the game.
     * </p>
     * @see BackgroundManager#getInstance()
     */

    private static BackgroundManager instance;

    /**
     * A property representing the background colour of the game.
     * <p>
     * Holds a Color value representing the background colour of the game's UI.
     * <br><br>
     *      It is initialized to Color with WHITE colour as the default colour
     * </p>
     * @see javafx.beans.property.ObjectProperty
     * @see javafx.scene.paint.Color
     */

    private final ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<>(Color.WHITE);

    /**
     * This private constructor ensures that the {@code BackgroundManager} class follows the
     * Singleton Design Pattern, where only one instance of the class can be created
     * throughout the whole runtime of a game.
     * <p>
     *      The instance is retrieved through the getInstance() method.
     * </p>
     * @see #getInstance()
     */

    private BackgroundManager() {}

    /**
     * This method ensures only one singleton exists, following the Singleton Design Pattern.
     * <p>
     *      If it detects that no singleton of instance exist, it will create a new instance.
     * </p>
     * @return The singleton instance of {@code BackgroundManager}
     */
    public static BackgroundManager getInstance() {
        if (instance == null) {
            instance = new BackgroundManager();
        }
        return instance;
    }

    /**
     * Gets the current background colour used in the game's UI
     * by retrieving the value of the backgroundColor property.
     *
     * @return The current background colour of the game.
     */
    public Color getBackgroundColor() {
        return backgroundColor.get();
    }

    public ObjectProperty<Color> backgroundColorProperty() {
        return backgroundColor;
    }

    /**
     * Sets the background colour of the game to the specified colour
     * by updating the value of the backgroundColor property.
     *
     * @param color The new background colour to be set
     */
    public void setBackgroundColor(Color color) {
        backgroundColor.set(color);
    }

    /**
     * Updates the background colour of the given AnchorPane.
     *
     * @param rootPane the AnchorPane to update.
     * @param color the new background colour.
     */
    public void updateBackgroundColor(AnchorPane rootPane, Color color) {
        if (color != null) {
            rootPane.setStyle("-fx-background-color: " + colorToHex(color) + ";");
        }
    }

    /**
     * Sets up the background image for the given ImageView and AnchorPane.
     *
     * @param rootPane the root AnchorPane.
     * @param backgroundImageView the ImageView for the background image.
     */
    public void setupBackground(AnchorPane rootPane, ImageView backgroundImageView) {
        backgroundImageView.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(rootPane.heightProperty());
    }

    /**
     * Converts a Color object to its hexadecimal string representation.
     *
     * @param color the Colour object to convert.
     * @return The hexadecimal string representation of the colour.
     */
    private String colorToHex(Color color) {
        final int MAX_COLOR_VALUE = 255;
        return String.format("#%02x%02x%02x",
                (int) (color.getRed() * MAX_COLOR_VALUE),
                (int) (color.getGreen() * MAX_COLOR_VALUE),
                (int) (color.getBlue() * MAX_COLOR_VALUE));
    }
}
