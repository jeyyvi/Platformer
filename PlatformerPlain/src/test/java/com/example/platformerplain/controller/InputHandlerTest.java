package com.example.platformerplain.controller;

import com.example.platformerplain.command.Command;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the InputHandler class.
 *
 * It verifies the following functionalities:
 * - Setting and retrieving commands.
 * - Handling key presses and executing associated commands.
 * - Handling key releases.
 *
 * @see InputHandler
 */
public class InputHandlerTest {
    private InputHandler inputHandler;
    private MockCommand mockCommand;

    /**
     * Sets up the test environment before each test.
     * Initializes the InputHandler and a mock command.
     */
    @BeforeEach
    public void setUp() {
        inputHandler = new InputHandler();
        mockCommand = new MockCommand();
    }

    /**
     * Tests that a command is correctly set and retrieved for a key press.
     */
    @Test
    public void testSetCommand() {
        inputHandler.setCommand(KeyCode.SPACE, mockCommand);
        assertNotNull(inputHandler.isPressed(KeyCode.SPACE));
    }

    /**
     * Tests that the input handler correctly processes key presses and executes the associated commands.
     */
    @Test
    public void testHandlePlayerInput() {
        inputHandler.setCommand(KeyCode.SPACE, mockCommand);
        inputHandler.setKeyPressed(KeyCode.SPACE, true);
        inputHandler.handlePlayerInput();
        assertTrue(mockCommand.executed, "Command should be executed when SPACE key is pressed.");
    }

    /**
     * Tests that the input handler correctly identifies when a key is not pressed.
     */
    @Test
    public void testKeyRelease() {
        inputHandler.setKeyPressed(KeyCode.SPACE, false);
        assertFalse(inputHandler.isPressed(KeyCode.SPACE), "SPACE key should not be pressed.");
    }

    /**
     * A mock command implementation for testing purposes.
     * It contains a flag to indicate if the command was executed.
     */
    private static class MockCommand implements Command {
        boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }
    }
}
