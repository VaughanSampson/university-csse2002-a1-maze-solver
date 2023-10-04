package io;

import exceptions.MazeMalformedException;

import exceptions.MazeSizeMissmatchException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Tests the FileMazeLoader class.
 */
class FileMazeLoaderTest {

    /** Loader variable holding instance to test. */
    private final FileMazeLoader loader = new FileMazeLoader();

    /** Path to the folder of test mazes. */
    private final String folderPath = new File("")
            .getAbsolutePath()+"\\test\\mazes\\";

    /**
     * Tests if the loader correctly throws a MazeMalformedException
     * when either of the dimensions it reads are even.
     */
    @Test
    public void testMazeMalformedExceptionOnEvenDimensions(){
        String filePath = folderPath + "even_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeMalformedException
     * when either of the dimensions are missing.
     */
    @Test
    public void testMazeMalformedExceptionOnMissingDimensions(){
        String filePath = folderPath + "missing_dimensions_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeMalformedException
     * when both dimensions are missing.
     */
    @Test
    public void testMazeMalformedExceptionOnNoDimensions(){
        String filePath = folderPath + "no_dimensions_line_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws an IllegalArgumentException
     * when a dimension is not in correct digit format.
     */
    @Test
    public void testIllegalArgumentExceptionOnInvalidDimensions(){
        String filePath = folderPath + "invalid_dimensions_maze.txt";
        assertThrows(
                IllegalArgumentException.class,
                () -> loader.load(filePath),
                "Expected load() to throw IllegalArgumentException" +
                        " but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeMalformedException
     * when there is no text in the given file.
     */
    @Test
    public void testMazeMalformedExceptionOnNoText(){
        String filePath = folderPath + "no_text_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeSizeMissmatchException
     * when there are not as many rows as the dimensions defined.
     */
    @Test
    public void testMazeSizeMissmatchExceptionOnShortMaze(){
        String filePath = folderPath + "too_short_maze.txt";
        assertThrows(
                MazeSizeMissmatchException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeSizeMissmatchException" +
                        " but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeSizeMissmatchException
     * when a row has too little tiles compared to dimensions defined.
     */
    @Test
    public void testMazeSizeMissmatchExceptionOnMissingTile(){
        String filePath = folderPath + "missing_tile_maze.txt";
        assertThrows(
                MazeSizeMissmatchException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeSizeMissmatchException" +
                        " but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws an IllegalArgumentException
     * when a symbol is invalid and cannot be used in Maze construction.
     */
    @Test
    public void testIllegalArgumentExceptionOnUnmatchedSymbol(){
        String filePath = folderPath + "unmatched_tile_maze.txt";
        assertThrows(
                IllegalArgumentException.class,
                () -> loader.load(filePath),
                "Expected load() to throw IllegalArgumentException" +
                        " but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeMalformedException
     * when there are not the correct number of endpoints in the file loaded.
     */
    @Test
    public void testMazeMalformedExceptionOnIncorrectNumberOfEnds(){
        String filePath = folderPath + "multiple_ends_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a MazeMalformedException
     * when there are not the correct number of startpoints in the file loaded.
     */
    @Test
    public void testMazeMalformedExceptionOnIncorrectNumberOfStarts(){
        String filePath = folderPath + "no_starts_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException but it didn't"
        );
    }

    /**
     * Tests if the loader correctly throws a FileNotFoundException
     * when the given path leads to no file.
     */
    @Test
    public void testFileNotFoundException(){
        String filePath = folderPath + "missing_file.txt";
        assertThrows(
                FileNotFoundException.class,
                () -> loader.load(filePath),
                "Expected load() to throw FileNotFoundException, but it didn't"
        );
    }

    /**
     * Tests if the loader correctly loads a 2D char array when given a
     * valid path to a valid file.
     */
    @Test
    public void testCorrectLoad(){
        String filePath = folderPath + "working_small_maze.txt";
        char[][] loaded = null;

        // Try load from the filepath
        try{
            loaded = loader.load(filePath);
        } catch (Exception e)
        {
            e.printStackTrace();
            // Cause an assertion fail as no exception should be thrown
            fail("Expected load() to throw no exception, " +
                    "but exception was thrown");
        }

        // Assert that the loader didn't return null
        assertNotNull(loaded,
                "Expected not null result from load. Result was null");

        // Expected 2D char array result
        char[][] expected = {
                {'#', '#','#', '#','#'},
                {'#', 'S',' ', ' ','#'},
                {'#', ' ','#', ' ','#'},
                {'#', ' ','#', 'E','#'},
                {'#', '#','#', '#','#'}
        };

        // Assert that the expected and loaded values are deeply equal
        assertTrue(Arrays.deepEquals(expected, loaded),
                "Expected result of load does not match result.");
    }
}