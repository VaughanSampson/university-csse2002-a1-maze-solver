package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MazeTest {

    private final char[][] map = {
            {'#', '#','#', '#','#'},
            {'#', 'S',' ', 'E','#'},
            {'#', '#','#', '#','#'}
    };

    Maze maze = new Maze(map);

    @Test
    public void testCreatingMaze(){

        MazeTile[][] expectedMazeTiles = {
                {Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall},
                {Maze.Wall, Maze.Start, Maze.Space, Maze.End, Maze.Wall},
                {Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall, Maze.Wall}
        };

        assertEquals(maze.getWidth(), expectedMazeTiles.length,"Maze width does not match expected value.");
        assertEquals(maze.getHeight(),  expectedMazeTiles[0].length,"Maze height does not match expected value.");

        for(int x = 0; x < maze.getWidth(); x++){
            for(int y = 0; y < maze.getHeight(); y++){
                assertEquals(expectedMazeTiles[x][y], maze.getTile(x,y), "Maze tile at (" + x + ", " + y +
                        "does not match expected type.");
            }
        }
    }

    @Test
    public void testGettingStart(){
        assertEquals(maze.getStart(), new Position(1,1), "Expected start to be at (1, 1).");
    }

    @Test
    public void testGettingEnd(){
        assertEquals(maze.getEnd(), new Position(1,3), "Expected end to be at (3, 1).");
    }

    @Test
    public void testSettingTile(){
        maze.setTile(1,2, Maze.Wall);
        assertEquals(Maze.Wall, maze.getTile(1,2), "Expected tile at (1, 2) to become a wall.");
    }

}