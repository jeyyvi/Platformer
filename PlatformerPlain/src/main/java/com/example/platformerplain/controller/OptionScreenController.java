package com.example.platformerplain.controller;

import com.example.platformerplain.view.MainScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * Controls the options screen of the game.
 * <p>
 *     Etends {@code ScreenBaseController}
 *     and handles the initialisation and user interactions on the
 *     options screen, such as applying colour changes and navigating the player
 *     back to the main menu.
 * </p>
 */
public class OptionScreenController extends ScreenBaseController {

    /**
     * The color picker for selecting background colour.
     */
    @FXML
    ColorPicker colorPicker;

    /**
     * The button to apply the selected options.
     */
    @FXML
    private Button applyBtn;

    /**
     * The button to navigate back to the main menu.
     */
    @FXML
    private Button backToMenuBtn;

    /**
     * Initialises the options screen controller.
     * <p>
     *     The {@code initialize} method is called to initialise the
     *     options screen controller.
     *     <br>,br>
     *     It sets the color picker's value to the current global background color.
     * </p>
     */
    @FXML
    public void initialize() {
        super.initialize();

        // Initialize ColorPicker with the current global background color
        colorPicker.setValue(BackgroundManager.getInstance().getBackgroundColor());
    }

    /**
     * Handles the action of applying the selected options.
     *<p>
     *     The handleApplyAction method is called when
     *     the player clicks on the 'Apply' Button. It updates the background
     *     colour of the root pane and sets the global background color in
     *     the 'BackgroundManager'.
     *</p>
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleApplyAction(ActionEvent event) {
        System.out.println("Apply button clicked");
        Color selectedColor = colorPicker.getValue();
        BackgroundManager.getInstance().updateBackgroundColor(rootPane, selectedColor);

        // Update the global background color in BackgroundManager
        BackgroundManager.getInstance().setBackgroundColor(selectedColor);
    }

    /**
     * Handles the action of navigating back to the main menu.
     * <p>
     *     The {@code handleBackToMenuAction} method brings the player
     *     back to the main menu when the "Back to Menu" Button is clicked.
     * </p>
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleBackToMenuAction(ActionEvent event) {
        System.out.println("Back to Menu button clicked");
        Stage primaryStage = (Stage) backToMenuBtn.getScene().getWindow();
        new MainScreen(primaryStage).show();
    }
}
