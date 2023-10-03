package io;

import com.sun.javaws.exceptions.InvalidArgumentException;
import exceptions.MazeMalformedException;

import exceptions.MazeSizeMissmatchException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FileMazeLoaderTest {

    FileMazeLoader loader;

    @BeforeEach
    void setUp() {
        loader = new FileMazeLoader();
    }

    @Test
    public void testMazeMalformedExceptionOnEvenDimensions(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"even_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException, but it didn't"
        );
    }

    @Test
    public void testMazeMalformedExceptionOnMissingDimensions(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"missing_dimensions_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException, but it didn't"
        );
    }

    @Test
    public void testMazeMalformedExceptionOnNoDimensions(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"no_dimensions_line_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException, but it didn't"
        );
    }

    @Test
    public void testIllegalArgumentExceptionOnInvalidDimensions(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"invalid_dimensions_maze.txt";
        assertThrows(
                IllegalArgumentException.class,
                () -> loader.load(filePath),
                "Expected load() to throw IllegalArgumentException, but it didn't"
        );
    }

    @Test
    public void testMazeMalformedExceptionOnNoText(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"no_text_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException, but it didn't"
        );
    }

    @Test
    public void testMazeSizeMissmatchExceptionOnShortMaze(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"too_short_maze.txt";
        assertThrows(
                MazeSizeMissmatchException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeSizeMissmatchException, but it didn't"
        );
    }

    @Test
    public void testMazeSizeMissmatchExceptionOnMissingTile(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"missing_tile_maze.txt";
        assertThrows(
                MazeSizeMissmatchException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeSizeMissmatchException, but it didn't"
        );
    }

    @Test
    public void testIllegalArgumentExceptionOnUnmatchedSymbol(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"unmatched_tile_maze.txt";
        assertThrows(
                IllegalArgumentException.class,
                () -> loader.load(filePath),
                "Expected load() to throw IllegalArgumentException, but it didn't"
        );
    }

    @Test
    public void testMazeMalformedExceptionOnIncorrectNumberOfEnds(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"multiple_ends_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException, but it didn't"
        );
    }

    @Test
    public void testMazeMalformedExceptionOnNoStarts(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"no_starts_maze.txt";
        assertThrows(
                MazeMalformedException.class,
                () -> loader.load(filePath),
                "Expected load() to throw MazeMalformedException, but it didn't"
        );
    }

    @Test
    public void testFileNotFoundException(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"missing_file.txt";
        assertThrows(
                FileNotFoundException.class,
                () -> loader.load(filePath),
                "Expected load() to throw FileNotFoundException, but it didn't"
        );
    }

    @Test
    public void correctResultFromFunction(){
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"working_small_maze.txt";
        char[][] loaded = null;

        try{
            loaded = loader.load(filePath);
        } catch (Exception e)
        {
            e.printStackTrace();
            fail("Expected load() to throw no exception, but exception was thrown");
        }

        assertNotNull(loaded,"Expected not null result from load. Result was null.");

        char[][] expected = {
                {'#', '#','#', '#','#'},
                {'#', 'S',' ', ' ','#'},
                {'#', ' ','#', ' ','#'},
                {'#', ' ','#', 'E','#'},
                {'#', '#','#', '#','#'}
        };

        assertTrue(Arrays.deepEquals(expected, loaded), "Expected result of load does not match result.");

    }
}