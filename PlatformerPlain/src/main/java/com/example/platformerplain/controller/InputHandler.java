package com.example.platformerplain.controller;

import com.example.platformerplain.command.Command;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the player's input actions.
 * <p>
 *     The {@code InputHandler} class manages the mapping of keyboard
 *     inputs to specific commands. It processes player input actions
 *     such as key presses, and executes the corresponding commands
 *     mapped to those keys.
 *     <br><br>
 *     When the key 'A' is pressed, the player moves to the left.<br>
 *     When the key 'S' is pressed, the player moves to the right.<br>
 *     When the key 'W' is pressed, the player jumps up.<br>
 *     When the space bar is pressed, the player launches fireballs.
 * </p>
 */
public class InputHandler {

    /**
     * Maps keyboard keys to their corresponding commands.
     */
    private Map<KeyCode, Command> commandMap = new HashMap<>();

    /**
     * Sets a command for a specific key.
     *
     * @param key the key code to map the command to
     * @param command the command to execute when the key is pressed
     */
    public void setCommand(KeyCode key, Command command) {
        commandMap.put(key, command);
    }

    /**
     * Handles the player's input actions by executing the corresponding commands.
     *
     * <p>
     *     The handlePlayerInput function iterates through the mapped key-command pairs,
     *     checking if the keys are pushed. When a key is pressed, the
     *     corresponding command is executed.
     * </p>
     */
    public void handlePlayerInput() {
        // Iterate through all key-command mappings in the commandMap
        for (Map.Entry<KeyCode, Command> entry : commandMap.entrySet()) {
            // Check if the current key is pressed
            if (isPressed(entry.getKey())) {
                entry.getValue().execute();
            }
        }
    }

    /**
     * Tracks the pressed status of keyboard keys.
     */
    // Existing methods to handle key presses
    private Map<KeyCode, Boolean> keys = new HashMap<>();

    /**
     * Sets the pressed status of a key.
     *
     * <p>
     *     The setKeyPressed method records whether the key
     *     is currently pressed or not.
     * </p>
     *
     * @param key the key code to set the pressed status for
     * @param isPressed true if the key is pressed, false if it is not
     */
    public void setKeyPressed(KeyCode key, boolean isPressed) {
        keys.put(key, isPressed);
    }

    /**
     * Checks if a key is pressed.
     *
     * @param key the key code to check the pressed status for
     *
     * @return True if the key is pressed, false if it is not
     */
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}
