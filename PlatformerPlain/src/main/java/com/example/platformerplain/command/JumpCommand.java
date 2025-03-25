package com.example.platformerplain.command;

import com.example.platformerplain.controller.PlayerController;

/**
 * A command to make the player jump.
 *  <p>
 *     Implements the {@code Command} interface and defines the behaviour for moving the player character to jump.
 *  </p>
 */
public class JumpCommand implements Command {

    /**
     * The controller of the player character.
     */
    private PlayerController playerController;

    /**
     * Constructs a new {@code JumpCommand} with the specified player controller.
     *
     * @param playerController the controller of the player character
     */
    public JumpCommand(PlayerController playerController) {
        this.playerController = playerController;
    }


    /**
     * Executes the jump command.
     */
    @Override
    public void execute() {
        playerController.jump();
    }
}
