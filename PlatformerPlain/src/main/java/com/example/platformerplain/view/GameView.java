package com.example.platformerplain.view;

import javafx.scene.layout.AnchorPane;

/**
 * Represents the view for the game.
 * <p>
 *     The {@code GameView} class class is responsible for the layout and organisation
 *     of the different UI components and game elements in the game view.
 *     <br><br>
 *     It provides methods for accessing the root panes of the application,
 *     game area, and user interface.
 * </p>
 */
public class GameView {

    /**
     * The root pane of the application.
     */
    private AnchorPane appRoot = new AnchorPane();

    /**
     * The root pane of the game area.
     */
    private AnchorPane gameRoot = new AnchorPane();

    /**
     * The root pane of the user interface.
     */
    private AnchorPane uiRoot = new AnchorPane();

    /**
     * The {@code GameView} class constructs a new {@code GameView} and initialises
     * the game layout.
     */
    public GameView() {
        gameRoot.setPrefHeight(720); // set the preferred height for the game area
        appRoot.getChildren().addAll(gameRoot, uiRoot);
    }

    /**
     * Returns the root pane of the application.
     *
     * @return The application's root pane
     */
    public AnchorPane getAppRoot() {
        return appRoot;
    }

    /**
     * Returns the root pane of the game area.
     *
     * @return The game area's root pane
     */
    public AnchorPane getGameRoot() {
        return gameRoot;
    }

    /**
     * Returns the root pane of the user interface.
     *
     * @return The user interface's root pane
     */
    public AnchorPane getUiRoot() {
        return uiRoot;
    }
}
