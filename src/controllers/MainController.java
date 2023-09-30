package controllers;

import models.Maze;
import view.MasterFrame;

public class MainController {

    private Maze maze;
    private MasterFrame masterView;

    public MainController(){
        masterView = new MasterFrame();
        maze = LoadMap("small.txt");
        masterView.beginNewMazeCanvas();
        masterView.setMazeColorMap(maze.getColorMap());
    }

    private static Maze LoadMap(String fileName){
        try {
            return new Maze(fileName);
        } catch (Exception e) {
            // DEAL WITH EXCEPTION
        }
        return null;
    }
}
