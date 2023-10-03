package controllers;

import io.FileMazeLoader;
import models.Maze;
import exceptions.MazeMalformedException;

import exceptions.MazeSizeMissmatchException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PathFinderTest {

    FileMazeLoader loader;

    @BeforeEach
    void setUp() {
        loader = new FileMazeLoader();
    }

    @Test
    public void testImpossibleMaze() {
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"impossible_maze.txt";
        char[][] loaded = null;

        try{
            loaded = loader.load(filePath);
        } catch (Exception e)
        {
            e.printStackTrace();
            fail("Expected FileMazeLoader load() to throw no exception, but exception was thrown");
        }

        Maze impossibleMaze = new Maze(loaded);

        assertNull(PathFinder.findAndDrawPathToEndpoint(impossibleMaze), "Expected to get null from " +
                "pathfinder findAndDrawPathToEndpoint() when impossible maze was passed in.");
    }


    @Test
    public void testSolvableMaze() {
        String filePath = new File("").getAbsolutePath()+"\\test\\mazes\\"+"possible_maze.txt";
        char[][] loaded = null;

        try{
            loaded = loader.load(filePath);
        } catch (Exception e)
        {
            e.printStackTrace();
            fail("Expected FileMazeLoader load() to throw no exception, but exception was thrown");
        }

        Maze possibleMaze = new Maze(loaded);

        assertNotNull(PathFinder.findAndDrawPathToEndpoint(possibleMaze), "Expected to get not null result " +
                "from pathfinder findAndDrawPathToEndpoint() when a solvable maze was passed in.");
    }
}