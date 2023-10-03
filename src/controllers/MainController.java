package controllers;

import io.FileMazeLoader;
import models.Maze;
import view.View;
import view.cmdline.CMDLineView;
import view.gui.GUIView;

import java.io.File;

public class MainController {

    public static void solveAndDisplayMaze(String mazePath, boolean usesGUI){

        // Choose view type
        View masterView;
        if(usesGUI)
            masterView = new GUIView();
        else
            masterView = new CMDLineView();

        // Load maze and render solution
        Maze maze = CreateMazeFromFile(mazePath);
        String textDisplay;

        if(maze != null) {
            textDisplay = (PathFinder.findAndDrawPathToEndpoint(maze) != null)? "Exit found." : "Maze unsolvable.";
        }
        else {
            textDisplay = "Maze " + mazePath + " unsuccessfully loaded.";
        }

        masterView.generateDisplay(maze, textDisplay);
    }

    private static Maze CreateMazeFromFile(String fileName){
        // Get complete path
        String filePath = new File("").getAbsolutePath()+"\\mazes\\"+fileName;
        Maze maze = null;

        try {
            // Try load
            char[][] map = new FileMazeLoader().load(filePath);
            if(map != null) {
                maze = new Maze(map);
            }
        } catch (Exception e) {
            // Log error on fail load
            System.out.println("Failed to load: " + filePath);
            e.printStackTrace();
        }

        return maze;
    }

}
