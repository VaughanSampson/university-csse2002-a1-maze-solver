package controllers;

import models.Maze;
import models.Position;

import java.util.ArrayList;

/**
 * C
 */
public class PathFinder {
    private static final Position[] possibleMovements = {
            new Position(1,0),
            new Position(0,-1),
            new Position(0,1),
            new Position(-1,0)
    };

    public static ArrayList<Position> drawPathToEndPoint(Maze maze){
        return drawPathToEndPoint(maze, maze.getStart());
    }

    public static ArrayList<Position> drawPathToEndPoint(Maze maze, Position currentPosition){
        ArrayList<Position> list = new ArrayList<>();
        list.add(currentPosition);

        if(currentPosition.equals(maze.getEnd())) {
            return list;
        }

        maze.setTile(currentPosition, Maze.Travelled);

        for(Position movement : possibleMovements){
            Position attemptMove = currentPosition.add(movement);
            if(maze.getTile(attemptMove).IsTraversable())
            {
                ArrayList<Position> subList = drawPathToEndPoint(maze, attemptMove);
                if(subList != null)
                {
                    maze.setTile(currentPosition, Maze.Path);
                    list.addAll(subList);
                    return list;
                }
            }
        }

        return null;
    }
}
