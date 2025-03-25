package com.example.platformerplain.controller;

import com.example.platformerplain.view.MainScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controls the behavior of the information screen.
 * <p>
 *     Extends {@code ScreenBaseController}, manages
 *     initialisation and user interactions on the information screen, such as
 *     returning to the main menu.
 * </p>
 */
public class InfoScreenController extends ScreenBaseController {

    /**
     * The button to navigate back to the main menu.
     */
    @FXML
    private Button backToMenuBtn;

    /**
     * Initialises the information screen controller.
     */
    @FXML
    public void initialize() {
        super.initialize();
    }

    /**
     * Handles the action of navigating back to the main menu.
     * <p>
     *     The handleBackToMenuAction method is triggered when
     *     the player clicks onto the "Back To Menu" Button. It brings
     *     the player back to the main menu.
     * </p>
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleBackToMenuAction(ActionEvent event) {
        System.out.println("Back to Menu button clicked");
        Stage primaryStage = (Stage) backToMenuBtn.getScene().getWindow();
        // Create a new MainScreen instance and show it
        new MainScreen(primaryStage).show();
    }
}
