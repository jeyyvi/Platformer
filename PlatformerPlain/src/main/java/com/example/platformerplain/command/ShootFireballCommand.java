package com.example.platformerplain.command;

import com.example.platformerplain.controller.FireballController;
import com.example.platformerplain.model.Player;
import com.example.platformerplain.view.GameView;
import com.example.platformerplain.model.Fireball;

/**
 * A command to shoot a fireball.
 * <p>
 *     Implements the {@code Command} interface and defines the behaviour for shooting a fireball from the player character.
 *
 *     <br><br>
 *
 *     It uses the 'FireballController' to create and launch the fireball, which is then added to the game view.
 * </p>
 */
public class ShootFireballCommand implements Command {

    /**
     * The controller for managing fireball actions.
     */
    private FireballController fireballController;

    /**
     * The player character.
     */
    private Player player;

    /**
     * The game view.
     */
    private GameView view;

    /**
     * Constructs a new {@code ShootFireballCommand} with the given parameters.
     *
     * @param fireballController the controller for managing fireball actions
     * @param player the player character
     * @param view the game view
     */
    public ShootFireballCommand(FireballController fireballController, Player player, GameView view) {
        this.fireballController = fireballController;
        this.player = player;
        this.view = view;
    }

    /**
     * Executes the shoot fireball command.
     */
    @Override
    public void execute() {
        Fireball fireball = fireballController.shootFireball(
                player.getEntity().getTranslateX() + player.getEntity().getFitWidth(),
                player.getEntity().getTranslateY() + player.getEntity().getFitHeight() / 2 - 10
        );
        view.getGameRoot().getChildren().add(fireball.getEntity());
    }
}
