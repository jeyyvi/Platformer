package com.example.platformerplain.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

/**
 * Test class for MainScreenController.
 * This class uses TestFX to simulate user interactions and verify button functionality.
 */
public class MainScreenControllerTest extends ApplicationTest {

    private MainScreenController controller;

    /**
     * Starts the JavaFX application.
     * Loads the FXML file and sets up the controller and scene.
     *
     * @param stage the primary stage
     * @throws Exception if an error occurs during loading
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
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
     * Cleans up after each test.
     *
     * @throws Exception if an error occurs during cleanup
     */
    @AfterEach
    public void cleanUp() throws Exception {
        FxToolkit.hideStage();  // Hides and cleans up the stage
        release(new KeyCode[0]);
        release(new MouseButton[0]);
    }

    /**
     * Tests the start game button functionality.
     * Simulates a click on the start game button and verifies the game initialization logic.
     */
    @Test
    public void testHandleStartGameAction() {
        clickOn("#startGameBtn");
        assertNotNull(controller.levelSelector.getValue());
        assertThat(controller.levelSelectedText).hasText(controller.levelSelector.getValue());
    }

    /**
     * Tests the option button functionality.
     * Simulates a click on the option button and verifies the option screen is shown.
     */
    @Test
    public void testHandleOptionAction() {
        clickOn("#optionBtn");
        waitForFxEvents(); // Ensures JavaFX events are fully processed
        assertThat(window("Options")).isShowing();
    }

    /**
     * Tests the info button functionality.
     * Simulates a click on the info button and verifies the info screen is shown.
     */
    @Test
    public void testHandleInfoAction() {
        clickOn("#infoBtn");
        assertThat(window("Information")).isShowing();
    }

    /**
     * Tests the exit game button functionality.
     * Simulates a click on the exit game button and verifies the primary stage is closed.
     */
    @Test
    public void testHandleExitGameAction() {
        clickOn("#exitGameBtn");
        Stage primaryStage = (Stage) controller.exitGameBtn.getScene().getWindow();
        assertFalse(primaryStage.isShowing());
    }
}
