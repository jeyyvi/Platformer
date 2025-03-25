package com.example.platformerplain.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.assertions.api.Assertions.assertThat;

/**
 * Test class for OptionScreenController.
 * This class uses TestFX to simulate user interactions and verify button functionality.
 */
public class OptionScreenControllerTest extends ApplicationTest {

    private OptionScreenController controller;

    /**
     * Starts the JavaFX application.
     * Loads the FXML file and sets up the controller and scene.
     *
     * @param stage the primary stage
     * @throws Exception if an error occurs during loading
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/OptionScreen.fxml"));
        AnchorPane root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the JavaFX environment before each test.
     *
     * @throws Exception if an error occurs during initialization
     */
    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    /**
     * Tests the apply button functionality.
     * Simulates selecting a color from the ColorPicker and clicking the apply button.
     */
    @Test
    public void testHandleApplyAction() {
        // Select a color from the ColorPicker
        interact(() -> controller.colorPicker.setValue(Color.RED));

        // Click the apply button
        clickOn("#applyBtn");

        // Verify the selected color is applied
        Color selectedColor = BackgroundManager.getInstance().getBackgroundColor();
        assertThat(selectedColor).isEqualTo(Color.RED);
    }

    /**
     * Tests the back to menu button functionality.
     * Simulates a click on the back to menu button and verifies the main screen is shown.
     */
    @Test
    public void testHandleBackToMenuAction() {
        clickOn("#backToMenuBtn");
        assertThat(window("Main Screen")).isShowing();
    }
}
