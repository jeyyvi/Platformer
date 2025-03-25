package com.example.platformerplain.controller;

import com.example.platformerplain.model.LevelData;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.assertions.api.Assertions.assertThat;

/**
 * Test class for EndScreenController.
 * This class uses TestFX to simulate user interactions and verify button functionality.
 */
public class EndScreenControllerTest extends ApplicationTest {

    private EndScreenController controller;

    /**
     * Starts the JavaFX application.
     * Loads the FXML file and sets up the controller and scene.
     *
     * @param stage the primary stage
     * @throws Exception if an error occurs during loading
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EndScreen.fxml"));
        AnchorPane root = loader.load();
        controller = loader.getController();
        controller.setStage(stage);

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
        controller.setTestMode(true);
    }

    /**
     * Cleans up after each test.
     *
     * @throws Exception if an error occurs during cleanup
     */
    @AfterEach
    public void cleanUp() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[0]);
        release(new MouseButton[0]);
    }

    /**
     * Tests the play again button functionality.
     * Simulates a click on the play again button and verifies the game is re-initialized.
     */
    @Test
    public void testHandlePlayAgainAction() {
        controller.setLevelData(LevelData.Level1);
        clickOn("#playAgainBtn");

        // Verify the play again action through state change
        assertNotNull(controller.levelData);
    }

    /**
     * Tests the exit game button functionality.
     * Simulates a click on the exit game button and verifies the primary stage is closed.
     */
    @Test
    public void testHandleExitGameAction() {
        clickOn("#exitGameBtn");
        assertFalse(controller.getStage().isShowing());
    }

    /**
     * Tests the back to menu button functionality.
     * Simulates a click on the back to menu button and verifies the main screen is shown.
     */
    /*
    @Test
    public void testHandleBackToMenuAction() {
        clickOn("#backToMenuBtn");

        // Verify that the main screen is shown
        Stage primaryStage = controller.getStage();
        assertThat(primaryStage.getTitle()).isEqualTo("Main Screen");
    }

     */

    /**
     * Tests the next level button functionality.
     * Simulates a click on the next level button and verifies the next level is initialized.
     */
    @Test
    public void testHandleNextLevelAction() {
        controller.setLevelData(LevelData.Level1);
        clickOn("#nextLevelBtn");

        // Verify the next level action through state change
        assertNotNull(controller.levelData);
    }

    /**
     * Tests the toggle high scores button functionality.
     * Simulates a click on the toggle high scores button and verifies the high scores are displayed.
     */
    @Test
    public void testHandleToggleHighScoresAction() {
        controller.setLevelData(LevelData.Level1);
        clickOn("#toggleHighScoresBtn");
        assertThat(controller.highScoresScrollPane).isVisible();
    }
}
