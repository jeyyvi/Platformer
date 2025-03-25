package com.example.platformerplain.config;

/**
 *  This enum represents different ScreenType and encapsulate FXML file path and title of each screen.
 *
 * <br><br>
 *
 * ScreenType is an enum representing 4 game screens - Main Screen, Info Screen, Option Screen and End Screen.
 *
 * <br><br>
 *
 * There are 4 enum constraints - MAIN_SCREEN, OPTION_SCREEN, INFO_SCREEN and END_SCREEN.
 * Each enum constant has an associated FXML file path and a title and these values are passed to the private constructor of the ScreenType class.
 */
public enum ScreenType {

    /**
     * The main screen allows player to select level, enter option screen, info screen or to exit the game.
     */
    MAIN_SCREEN(Constants.MAIN_SCREEN_PATH, Constants.MAIN_SCREEN_TITLE),
    /**
     * The option screen allows player to change the game's background colour via a color palette.
     */
    OPTION_SCREEN(Constants.OPTION_SCREEN_PATH, Constants.OPTION_SCREEN_TITLE),
    /**
     * The info screen teaches the user how to play the game.
     */
    INFO_SCREEN(Constants.INFO_SCREEN_PATH, Constants.INFO_SCREEN_TITLE),
    /**
     * The end screen of the game displays player's highest score and current scores.
     */
    END_SCREEN(Constants.END_SCREEN_PATH, Constants.END_SCREEN_TITLE);

    /**
     * The file path to the FXML file for the screen.
     */
    private final String fxmlFilePath;

    /**
     * The title of the screen.
     */
    private final String title;

    /**
     * The ScreenType class constructs a new 'ScreenType' with the
     * given parameters - fxmlFilePath and title
     *
     * @param fxmlFilePath the file path to FXML file for the screen
     * @param title the title of the screen
     */
    ScreenType(String fxmlFilePath, String title) {
        this.fxmlFilePath = fxmlFilePath;
        this.title = title;
    }

    /**
     * Get the FXML file path that is associated with the specified screen type.
     *
     * @return A String value representing the relative path to the FXML file.
     */
    public String getFXMLFilePath() {
        return fxmlFilePath;
    }

    /**
     * Get the title associated with a specific screen type.
     * <br><br>
     * @return A String value representing the title to be displayed.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The {@code Constants} class contains constant values for file paths to FXML files for different screens and
     * the titles for each screen.
     * <br><br>
     * The constants defined in this class are:
     * <ul>
     *     <li>{@link #MAIN_SCREEN_PATH} - The file path for the main screen FXML.</li>
     *     <li>{@link #OPTION_SCREEN_PATH} - The file path for the options screen FXML.</li>
     *     <li>{@link #INFO_SCREEN_PATH} - The file path for the information screen FXML.</li>
     *     <li>{@link #END_SCREEN_PATH} - The file path for the end screen FXML.</li>
     *     <li>{@link #MAIN_SCREEN_TITLE} - The title of the main screen.</li>
     *     <li>{@link #OPTION_SCREEN_TITLE} - The title of the options screen.</li>
     *     <li>{@link #INFO_SCREEN_TITLE} - The title of the information screen.</li>
     *     <li>{@link #END_SCREEN_TITLE} - The title of the game over screen.</li>
     * </ul>
     */
    private static class Constants {
        /**
         * The file path to the main screen's FXML file.<br>
         * Used to load the main screen view.
         */
        static final String MAIN_SCREEN_PATH = "/View/mainScreen.fxml";

        /**
         * The file path to the options screen's FXML file.<br>
         * Used to load the options screen for configuring settings.
         */
        static final String OPTION_SCREEN_PATH = "/View/optionScreen.fxml";

        /**
         * The file path to the information screen's FXML file.<br>
         * Used to load the information screen, typically containing help or additional details.
         */
        static final String INFO_SCREEN_PATH = "/View/infoScreen.fxml";

        /**
         * The file path to the end screen's FXML file.<br>
         * Used to load the game over screen when the game concludes.
         */
        static final String END_SCREEN_PATH = "/View/endScreen.fxml";

        /**
         * The title displayed on the main screen.<br>
         * Used to set the window title for the main screen.
         */
        static final String MAIN_SCREEN_TITLE = "Main Screen";

        /**
         * The title displayed on the options screen.<br>
         * Used to set the window title for the options screen.
         */
        static final String OPTION_SCREEN_TITLE = "Options";

        /**
         * The title displayed on the information screen.<br>
         * Used to set the window title for the information screen.
         */
        static final String INFO_SCREEN_TITLE = "Information";

        /**
         * The title displayed on the end screen.<br>
         * Used to set the window title for the game over screen.
         */
        static final String END_SCREEN_TITLE = "Game Over";
    }
}