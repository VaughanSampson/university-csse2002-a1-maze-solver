package controllers;

import models.Maze;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests PathFinder class.
 */
class PathFinderTest {

    /**
     * 2D char array map used to generate a maze which is impossible to solve.
     */
    private final char[][] impossibleMazeMap = {
            {'#', '#','#', '#','#'},
            {'#', 'S',' ', ' ','#'},
            {'#', ' ','#', ' ','#'},
            {'#', ' ','#', ' ','#'},
            {'#', '#','#', ' ','#'},
            {'#', '#',' ', ' ','#'},
            {'#', ' ',' ', '#','#'},
            {'#', ' ','#', '#','#'},
            {'#', ' ',' ', ' ','#'},
            {'#', '#','#', ' ','#'},
            {'#', ' ','E', '#','#'},
            {'#', '#','#', '#','#'}
    };

    /**
     * 2D char array map used to generate a maze which can be solved.
     */
    private final char[][] possibleMazeMap = {
            {'#', '#','#', '#','#'},
            {'#', 'S',' ', ' ','#'},
            {'#', ' ','#', ' ','#'},
            {'#', ' ','#', ' ','#'},
            {'#', '#','#', ' ','#'},
            {'#', '#',' ', ' ','#'},
            {'#', ' ',' ', '#','#'},
            {'#', ' ','#', '#','#'},
            {'#', ' ',' ', ' ','#'},
            {'#', ' ','#', ' ','#'},
            {'#', ' ','E', '#','#'},
            {'#', '#','#', '#','#'}
    };

    /**
     * Tests if PathFinder correctly fails to solve an impossible maze.
     */
    @Test
    public void testImpossibleMaze() {
        // Create maze
        Maze impossibleMaze = new Maze(impossibleMazeMap);

        // Attempt to solve maze
        boolean mazeSolved = PathFinder.drawPathToEndPoint(impossibleMaze);

        // Assert that maze was not solved
        assertFalse(mazeSolved,"Expected to fail to find path but succeeded.");
    }

    /**
     * Tests if PathFinder correctly succeeds to solve a possible maze.
     */
    @Test
    public void testSolvableMaze() {
        // Create maze
        Maze impossibleMaze = new Maze(possibleMazeMap);

        // Attempt to solve maze
        boolean mazeSolved = PathFinder.drawPathToEndPoint(impossibleMaze);

        // Assert that the maze was solved
        assertTrue(mazeSolved, "Expected to succeed to find path but failed.");
    }
}