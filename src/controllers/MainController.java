package controllers;

import models.Maze;

public class MainController {

    private Maze maze;

    public MainController(){
    }

    public void LoadMap(String fileName){
        this.maze = new Maze(fileName);
    }

}
