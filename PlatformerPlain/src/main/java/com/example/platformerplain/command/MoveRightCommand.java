package com.example.platformerplain.command;

import com.example.platformerplain.controller.PlayerController;

/**
 * A command to move the player to the right.
 * <p>
 *     Implements the {@code Command} interface and defines the behaviour for moving the player character to the right.
 * </p>
 */
public class MoveRightCommand implements Command {

    /**
     * The controller of the player character.
     */
    private PlayerController playerController;

    /**
     * The distance to move the player right.
     */
    private int moveDistance;

    /**
     * Constructs a new {@code MoveRightCommand} with the specified player controller and move distance.
     *
     * @param playerController the controller of the player character
     * @param moveDistance the distance to move the player right
     */
    public MoveRightCommand(PlayerController playerController, int moveDistance) {
        this.playerController = playerController;
        this.moveDistance = moveDistance;
    }

    /**
     * Executes the move right command.
     */
    @Override
    public void execute() {
        playerController.moveX(moveDistance);
    }
}
