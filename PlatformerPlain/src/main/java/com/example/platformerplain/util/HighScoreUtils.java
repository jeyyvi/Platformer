package com.example.platformerplain.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides utility methods for reading and writing high scores.
 * <p>
 *     The {@code HighScoreUtils} class contains static methods for reading high scores
 *     from files, writing new high scores to files, and managing a
 *     list of high scores at different levels.
 *     <br><br>
 *     It also supports setting custom file names for testing purposes.
 * </p>
 */
public class HighScoreUtils {

    /**
     * The file name for storing high scores for Level 1.
     */
    private static String level1ScoreFile = "level1_highscores.txt";

    /**
     * The file name for storing high scores for Level 2.
     */
    private static String level2ScoreFile = "level2_highscores.txt";

    /**
     * The file name for storing high scores for Level 3.
     */
    private static String level3ScoreFile = "level3_highscores.txt";

    /**
     * The maximum number of high scores to keep.
     */
    private static final int MAX_HIGH_SCORES = 10;

    /**
     * Reads the high scores for the specified level.
     *
     * @param level the level name
     *
     * @return A list of high scores for the specified level
     */
    public static List<Integer> readHighScores(String level) {
        // Determine the file name for the given level
        String fileName = getFileName(level);
        // Read and return high scores from the file
        return readHighScoresFromFile(fileName);
    }

    /**
     * Writes a new high score for the specified level.
     *
     * @param level the level name
     * @param score the new high score to add
     */
    public static void writeHighScore(String level, int score) {
        // Identify the correct file for the level
        String fileName = getFileName(level);
        // Read existing scores
        List<Integer> highScores = readHighScores(level);
        // Add the new score and update the list
        highScores = addNewScore(highScores, score);
        // Save and update score to file
        writeHighScoresToFile(fileName, highScores);
    }

    /**
     * Reads high scores from the specified file.
     *
     * @param fileName the name of the file to read high scores from
     *
     * @return A list of high scores read from the file
     *
     * @throws NumberFormatException If a line in the file cannot be parsed as an integer.
     *
     */
    private static List<Integer> readHighScoresFromFile(String fileName) {
        List<Integer> highScores = new ArrayList<>();
        System.out.println("Reading high scores from file: " + fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) { // Read each line
                highScores.add(Integer.parseInt(line)); // Parse and add scores
            }
        } catch (FileNotFoundException e) {
            System.out.println("High score file not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading high scores from " + fileName + ": " + e.getMessage());
        }
        return highScores;
    }

    /**
     * Adds a new score to the list of high scores and sorts the list.
     *
     * @param highScores the list of existing high scores
     * @param score the new score to add
     *
     * @return The updated list of high scores
     */
    private static List<Integer> addNewScore(List<Integer> highScores, int score) {
        highScores.add(score);
        // Sort scores in descending order
        highScores.sort((a, b) -> Integer.compare(b, a));
        if (highScores.size() > MAX_HIGH_SCORES) {
            // Keep the list to the maximum allowed size
            highScores = highScores.subList(0, MAX_HIGH_SCORES);
        }
        return highScores;
    }

    /**
     * Writes the list of high scores to the specified file.
     *
     * @param fileName the name of the file to write high scores to
     * @param highScores the list of high scores to write
     *
     */
    private static void writeHighScoresToFile(String fileName, List<Integer> highScores) {
        System.out.println("Writing high scores to file: " + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int highScore : highScores) {
                writer.write(highScore + "\n"); // Write each score to the file
            }
            System.out.println("Wrote high scores to file: " + fileName + ": " + highScores);
        } catch (IOException e) {
            System.err.println("Error writing high scores to " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Returns the file name for the specified level.
     *
     * @param level the level name
     *
     * @return The name of the file for the specified level
     *
     * @throws IllegalArgumentException If the level name is unknown
     */
    private static String getFileName(String level) {
        switch (level) {
            case "Level 1":
                return level1ScoreFile;
            case "Level 2":
                return level2ScoreFile;
            case "Level 3":
                return level3ScoreFile;
            default:
                throw new IllegalArgumentException("Unknown level: " + level);
        }
    }

    /**
     * Sets custom file names for testing purposes.
     *
     * @param level1File the file name for Level 1 high scores
     * @param level2File the file name for Level 2 high scores
     * @param level3File the file name for Level 3 high scores
     */
    public static void setFileNamesForTesting(String level1File, String level2File, String level3File) {
        level1ScoreFile = level1File;
        level2ScoreFile = level2File;
        level3ScoreFile = level3File;
    }
}
