package com.example.platformerplain.controller;

import com.example.platformerplain.command.*;
import com.example.platformerplain.model.*;
import com.example.platformerplain.observer.ScoreModel;
import com.example.platformerplain.view.GameView;
import com.example.platformerplain.observer.ScoreView;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controls the game logic and interactions.
 *  <p>
 *      The {@code GameController} class handles the core functionality of the game, including player input, game updates,
 *      collision detection, and managing various game entities like the player, eagle, and fireballs.
 *  </p>
 */
public class GameController {

    /**
     * Handles the player's input actions.
     */
    private final InputHandler inputHandler;

    /**
     * Manages the player's movements and interactions
     */
    private final PlayerController playerController;

    /**
     * Manages the eagle's movements and interactions.
     */
    private final EagleController eagleController;

    /**
     * Manages the collection of money items in the game.
     */
    private final MoneyController moneyController;

    /**
     * Handles the physics interactions and collision detection in the game.
     */
    private final GamePhysics gamePhysics;

    /**
     * Loads and initializes the game levels.
     */
    private final LevelLoader levelLoader;

    /**
     * Updates the game state and entities.
     */
    private final GameUpdater gameUpdater;

    /**
     * Represents the game view.
     */
    private final GameView view;

    /**
     * Represents the player character.
     */
    private final Player player;

    /**
     * Represents the eagle entity.
     */
    private final Eagle eagle;

    /**
     * Manages the creation and interactions of fireballs.
     */
    private final FireballController fireballController;

    /**
     * Indicates whether the game is stopped.
     * <p>
     *     The boolean flag is true when the game is stopped and false if it is not.
     * </p>
     */
    private boolean gameStopped = false;

    /**
     * The primary stage of the application.
     * <p>
     *     Displays the main user interface elements and manage the overall game environment.
     * </p>
     */
    private final Stage primaryStage;

    /**
     * The animation timer for updating the game loop.
     * <p>
     *     Responsible for continuously updating the game loop.
     * </p>
     */
    private AnimationTimer timer;

    /**
     * The pattern of platforms of the levels in the game.
     * <p>
     *     The levelPattern field holds an array of strings representing
     *     the pattern of the platforms in the game.
     * </p>
     */
    private final String[] levelPattern;

    /**
     * Manages the player's score.
     */
    private final ScoreController scoreController;

    /**
     * Manages the game state transitions and updates.
     */
    private final GameStateController gameStateManager;

    /**
     * The file path to the fireball image.
     */
    public static final String FIRE_IMAGE_PATH = "/Image/fire.png";

    /**
     * The fireball's moving speed.
     */
    public static final double FIREBALL_SPEED = 10.0;

    /**
     * The distance the player moves per action.
     */
    public static final int MOVE_DISTANCE = 5;

    /**
     * The speed at which the eagle moves in Level 1.
     */
    public static final double LEVEL1_EAGLE_SPEED = 1.5;

    /**
     * The speed at which the eagle moves in Level 2.
     */
    public static final double LEVEL2_EAGLE_SPEED = 2.0;

    /**
     * The speed at which the eagle moves in Level 3.
     */
    public static final double LEVEL3_EAGLE_SPEED = 3.0;

    /**
     * Constructs a new {@code GameController} with the specified view, player,
     * primary stage, and level pattern.
     *
     * @param view the game view
     * @param player the player character
     * @param primaryStage the primary stage for the game
     * @param levelPattern the pattern of the levels
     */
    public GameController(GameView view, Player player, Stage primaryStage, String[] levelPattern) {
        this.view = view;
        this.player = player;
        this.primaryStage = primaryStage;
        this.eagle = new Eagle();
        LevelModel levelModel = new LevelModel();
        this.moneyController = new MoneyController(levelModel.getMoneyItems(), player.getEntity());
        levelModel.getMoneyController().setPlayerEntity(player.getEntity());
        this.levelLoader = new LevelLoader(view, levelModel, moneyController);
        this.levelPattern = levelPattern;
        this.gamePhysics = new GamePhysics(levelModel.getPlatforms());
        this.scoreController = new ScoreController(new ScoreModel(), new ScoreView(view.getUiRoot()), player, moneyController);
        this.playerController = new PlayerController(player, gamePhysics);
        this.gameStateManager = new GameStateController(this); // Initialize gameStateManager first
        this.eagleController = new EagleController(eagle, levelModel.getPlatforms(), calculateEagleSpeed(levelPattern), player, gameStateManager); // Now pass gameStateManager
        this.fireballController = new FireballController(FIRE_IMAGE_PATH, FIREBALL_SPEED);
        this.inputHandler = new InputHandler();
        this.gameUpdater = new GameUpdater(this, player, view, gameStateManager, scoreController, levelLoader);

        setupCommands();
        levelLoader.loadLevel(levelPattern);
        initPlayer();
        view.getGameRoot().getChildren().add(eagle.getEntity());
    }

    /**
     * Calculates the speed of the eagle based on the level pattern.
     *
     * @param levelPattern the pattern of the levels
     * @return The speed of the eagle
     */
    private double calculateEagleSpeed(String[] levelPattern) {
        return levelPattern == LevelData.Level1 ? LEVEL1_EAGLE_SPEED : (levelPattern == LevelData.Level2 ? LEVEL2_EAGLE_SPEED : LEVEL3_EAGLE_SPEED);
    }

    /**
     * Sets up the commands for player input.
     */
    private void setupCommands() {
        inputHandler.setCommand(KeyCode.A, new MoveLeftCommand(playerController, MOVE_DISTANCE));
        inputHandler.setCommand(KeyCode.D, new MoveRightCommand(playerController, MOVE_DISTANCE));
        inputHandler.setCommand(KeyCode.W, new JumpCommand(playerController));
        inputHandler.setCommand(KeyCode.SPACE, new ShootFireballCommand(fireballController, player, view));
    }

    /**
     * Initializes the player by adding the player's entity to the game root.
     */
    void initPlayer() {
        view.getGameRoot().getChildren().add(player.getEntity());
    }

    /**
     * Updates the game state, including player and eagle movements,
     * collisions, and score updates.
     */
    public void update() {
        if (!gameStopped) {
            gameStateManager.update(this);
            scoreController.update();
            eagleController.updateMovement(player.getEntity().getTranslateX(), player.getEntity().getTranslateY());
            eagleController.checkEagleCollision(this);
            gamePhysics.checkSnakeCollision(this);
            gamePhysics.updateFireballs(this);
            moneyController.collectMoney(player);
        }
    }

    /**
     * Handles player input by delegating to the input handler.
     */
    public void handlePlayerInput() {
        inputHandler.handlePlayerInput();
    }

    /**
     * Returns the game view.
     *
     * @return The game view
     */
    public GameView getView() {
        return view;
    }

    /**
     * Returns the player controller.
     *
     * @return The player controller
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Returns the player character.
     *
     * @return The player character
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the move distance for player movements.
     *
     * @return The move distance for player movements
     */
    public int getMoveDistance() {
        return MOVE_DISTANCE;
    }

    /**
     * Returns the score controller.
     *
     * @return The score controller
     */
    public ScoreController getScoreController() {
        return scoreController;
    }

    /**
     * Returns the primary stage for the game.
     *
     * @return The primary stage for the game
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Returns the list of snakes in the game.
     *
     * @return The list of snakes in the game
     */
    public List<Snake> getSnakes() {
        return levelLoader.getSnakes();
    }

    /**
     * Returns the input handler for player input.
     *
     * @return The input handler for player input
     */
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    /**
     * Returns the fireball controller.
     *
     * @return The fireball controller
     */
    public FireballController getFireballController() {
        return fireballController;
    }

    /**
     * Returns the eagle controller.
     * @return The eagle controller
     */
    public EagleController getEagleController() {
        return eagleController;
    }

    /**
     * Stops the game timer.
     */
    public void stopGameTimer() {
        gameStopped = true;
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Handles game state changes, such as transitioning to
     * the next level or ending the game.
     *
     * @param levelPassed true if the level was passed, false if it is not
     */
    public void handleGameStateChange(boolean levelPassed) {
        gameStateManager.handleGameStateChange(this, levelPassed, levelPattern);
    }

    /**
     * Starts the game by initiating the game timer.
     */
    public void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    /**
     * Updates the game by delegating to the game updater.
     */
    public void updateGame() {
        gameUpdater.updateGame();
    }
}
