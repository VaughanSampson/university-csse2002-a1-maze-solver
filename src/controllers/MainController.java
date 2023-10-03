package controllers;

import io.FileMazeLoader;
import models.Maze;
import view.View;
import view.terminal.TerminalView;
import view.gui.GUIView;

/**
 * Controller which allows a user to load, solve and display a maze.
 */
public class MainController {

    /**
     * Static method to solve and display a maze loaded from a file.
     * @param filePath The path of the maze to load and solve.
     * @param usesGUI Whether to use GUI or (otherwise) a terminal interface.
     */
    public static void solveAndDisplayMaze(String filePath, boolean usesGUI){

        // Choose view type (GUI or terminal)
        View masterView;
        if(usesGUI)
            masterView = new GUIView();
        else
            masterView = new TerminalView();

        // Generate maze from file data
        Maze maze = CreateMazeFromFile(filePath);

        // Render
        String textDisplay;
        if(maze != null) {
            // Alter maze as path is travelled
            boolean exitFound = PathFinder.drawPathToEndPoint(maze);
            // Set created message to describe if the maze was solvable
            textDisplay = (exitFound)? "Exit found." : "Maze unsolvable.";
        }
        else {
            // Set created message to display that the file path was not
            // successfully loaded.
            textDisplay = "Maze " + filePath + " unsuccessfully loaded.";
        }

        // Render maze and message to view.
        masterView.generateDisplay(maze, textDisplay);
    }

    /**
     * Gets a completed Maze instance from a file path if the file path points
     * to a suitable maze file.
     * @param filePath Path to maze file.
     * @return Maze instance generated or null if generation failed.
     */
    private static Maze CreateMazeFromFile(String filePath){

        Maze maze = null;

        try {
            // Try load a 2D maze char map from file
            char[][] map = new FileMazeLoader().load(filePath);
            // If no errors occur, create the Maze instance to be returned
            if(map != null) {
                maze = new Maze(map);
            }
        } catch (Exception e) {
            // Log error on failure to load a 2D maze char map
            System.out.println("Failed to load: " + filePath);
            e.printStackTrace();
        }

        return maze;
    }

}
