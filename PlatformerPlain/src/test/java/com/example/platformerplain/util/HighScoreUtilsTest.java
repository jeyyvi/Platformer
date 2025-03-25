package com.example.platformerplain.util;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the HighScoreUtils class.
 * This class contains unit tests for reading and writing high scores.
 */
public class HighScoreUtilsTest {
    private static final String TEMP_FILE_PREFIX = "temp_";
    private static final String LEVEL1_SCORE_FILE = "level1_highscores.txt";
    private static final String LEVEL2_SCORE_FILE = "level2_highscores.txt";
    private static final String LEVEL3_SCORE_FILE = "level3_highscores.txt";

    /**
     * Sets up the test environment before each test.
     * Creates temporary files for each level with initial data.
     *
     * @throws IOException if an I/O error occurs.
     */
    @BeforeEach
    public void setUp() throws IOException {
        createTempFileWithInitialData(LEVEL1_SCORE_FILE, new int[]{});
        createTempFileWithInitialData(LEVEL2_SCORE_FILE, new int[]{});
        createTempFileWithInitialData(LEVEL3_SCORE_FILE, new int[]{});

        HighScoreUtils.setFileNamesForTesting(
                TEMP_FILE_PREFIX + LEVEL1_SCORE_FILE,
                TEMP_FILE_PREFIX + LEVEL2_SCORE_FILE,
                TEMP_FILE_PREFIX + LEVEL3_SCORE_FILE
        );
    }

    /**
     * Cleans up the test environment after each test.
     * Deletes temporary files.
     */
    @AfterEach
    public void tearDown() {
        deleteTempFile(LEVEL1_SCORE_FILE);
        deleteTempFile(LEVEL2_SCORE_FILE);
        deleteTempFile(LEVEL3_SCORE_FILE);
    }

    /**
     * Tests reading high scores from an empty file.
     * Ensures the returned list is not null and is empty.
     */
    @Test
    public void testReadHighScores() {
        List<Integer> highScores = HighScoreUtils.readHighScores("Level 1");
        assertNotNull(highScores);
        assertTrue(highScores.isEmpty());
    }

    /**
     * Tests writing a high score to the file.
     * Ensures the high score is saved and can be read back correctly.
     */
    @Test
    public void testWriteHighScore() {
        HighScoreUtils.writeHighScore("Level 1", 100);
        List<Integer> highScores = HighScoreUtils.readHighScores("Level 1");
        assertNotNull(highScores);
        assertEquals(1, highScores.size());
        assertEquals(100, highScores.get(0).intValue());
    }

    /**
     * Tests writing multiple high scores to the file.
     * Ensures the high scores are saved in descending order.
     */
    @Test
    public void testWriteMultipleHighScores() {
        HighScoreUtils.writeHighScore("Level 1", 100);
        HighScoreUtils.writeHighScore("Level 1", 200);
        List<Integer> highScores = HighScoreUtils.readHighScores("Level 1");
        assertNotNull(highScores);
        assertEquals(2, highScores.size());
        assertEquals(200, highScores.get(0).intValue());
        assertEquals(100, highScores.get(1).intValue());
    }

    /**
     * Tests adding more than the maximum allowed high scores.
     * Ensures only the top scores are kept.
     */
    @Test
    public void testAddMoreThanMaxHighScores() {
        for (int i = 0; i < 15; i++) {
            HighScoreUtils.writeHighScore("Level 1", i * 10);
        }
        List<Integer> highScores = HighScoreUtils.readHighScores("Level 1");
        assertNotNull(highScores);
        assertEquals(10, highScores.size());
        assertEquals(140, highScores.get(0).intValue());
        assertEquals(50, highScores.get(9).intValue());
    }

    /**
     * Creates a temporary file with initial data.
     *
     * @param fileName the name of the file to create.
     * @param scores   the initial scores to write to the file.
     * @throws IOException if an I/O error occurs.
     */
    private void createTempFileWithInitialData(String fileName, int[] scores) throws IOException {
        File tempFile = new File(TEMP_FILE_PREFIX + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (int score : scores) {
                writer.write(score + "\n");
            }
        }
    }

    /**
     * Deletes the specified temporary file.
     *
     * @param fileName the name of the file to delete.
     */
    private void deleteTempFile(String fileName) {
        File tempFile = new File(TEMP_FILE_PREFIX + fileName);
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }
}
