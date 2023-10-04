package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Maze class.
 */
class MazeTest {

    /** 2D character array used to generate a small maze. */
    private final char[][] charMap = {
            {'#', '#','#', '#','#'},
            {'#', 'S',' ', 'E','#'},
            {'#', '#','#', '#','#'}
    };

    /** A maze instance to be used in tests. */
    Maze maze;

    /**
     * Prepares for each test.
     */
    @BeforeEach
    public void initialise(){
        // Set the map to a new map using the small charMap as a blueprint
        maze = new Maze(charMap);
    }

    /**
     * Tests if a maze can be accurately generated.
     */
    @Test
    public void testCreatingMaze(){
        // Create a 2D array of the expected result
        MazeTile[][] expectedMazeTiles = {
                {Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall},
                {Maze.Wall, Maze.Start, Maze.Space, Maze.End, Maze.Wall},
                {Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall}
        };

        // Assert that the dimensions of the generated maze are as expected
        assertEquals(maze.getWidth(), expectedMazeTiles.length,"Maze width does not match expected value.");
        assertEquals(maze.getHeight(),  expectedMazeTiles[0].length,"Maze height does not match expected value.");

        // Deeply compare the maze's tile map to the expected tile structure.
        for(int x = 0; x < maze.getWidth(); x++){
            for(int y = 0; y < maze.getHeight(); y++){
                assertEquals(expectedMazeTiles[x][y], maze.getTile(x,y),
                        "Maze tile at (" + x + ", " + y + "does not " +
                                "match expected type.");
            }
        }
    }

    /**
     * Tests that the start position getter is valid.
     */
    @Test
    public void testGettingStart(){
        assertEquals(maze.getStart(), new Position(1,1), "Expected start to be at (1, 1).");
    }

    /**
     * Tests that the end position getter is valid.
     */
    @Test
    public void testGettingEnd(){
        assertEquals(maze.getEnd(), new Position(1,3), "Expected end to be at (3, 1).");
    }

    /**
     * Tests that the positional tile setter functions.
     */
    @Test
    public void testSettingTile(){
        maze.setTile(1,2, Maze.Wall);
        assertEquals(Maze.Wall, maze.getTile(1,2), "Expected tile at (1, 2) to become a wall.");
    }

}