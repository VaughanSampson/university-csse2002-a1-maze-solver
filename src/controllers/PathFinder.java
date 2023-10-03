package controllers;

import models.Maze;
import models.Position;

/**
 * Controller to alter a maze so that a path is drawn from the
 * start to its end if end is found.
 */
public class PathFinder {

    /**
     * A list of relative positions representing possible directional movements
     * through a maze.
     */
    private static final Position[] possibleRelativeMovements = {
            new Position(1,0),
            new Position(0,-1),
            new Position(0,1),
            new Position(-1,0)
    };

    /**
     * Begins drawing a path from the start point of the maze, to the end
     * point of the maze.
     * @param maze Maze to alter.
     * @return False if there was no path through the maze, otherwise true if a
     * path was found.
     */
    public static boolean drawPathToEndPoint(Maze maze){
        return drawPathToEndPoint(maze, maze.getStart());
    }

    /**
     * Recursively alters maze, plotting its search for the endpoint.
     * @param maze Maze to search through and alter.
     * @param currentPosition Current position in search.
     * @return False if there was no path through the maze, otherwise true if a
     * path was found.
     */
    private static boolean drawPathToEndPoint(
            Maze maze, Position currentPosition) {

        // Returns true if the current position is at the end point
        if(currentPosition.equals(maze.getEnd())) {
            return true;
        }

        // Makes the current position a travelled path tile. This
        // plots exploration to the map and prevents this tile being
        // travelled over again which could lead to inefficient paths
        // or a loop.
        maze.setTile(currentPosition, Maze.Travelled);

        // Iterates through and enacts relative movements
        for(Position movement : possibleRelativeMovements){
            Position attemptMove = currentPosition.add(movement);

            // If the movement is allowed
            if(maze.getTile(attemptMove).IsTraversable())
            {
                // Does a recursive call, continuing the movement. Gets
                // true if any descendant movement results in finding the end.
                boolean endFound = drawPathToEndPoint(maze, attemptMove);

                // If the end is found by a recursive descendant
                if(endFound)
                {
                    // Draw a path tile as this position is part of the
                    // successful path.
                    maze.setTile(currentPosition, Maze.Path);

                    // Recursively pass back true to represent success
                    return true;
                }
            }
        }

        // Return false as no relative movement lead to success
        return false;
    }
}
