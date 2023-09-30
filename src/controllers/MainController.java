package controllers;

import models.Maze;
import view.MasterView;

public class MainController {

    private Maze maze;
    private MasterView view;

    public MainController(){
    }

    public void LoadMap(String fileName){
        try {
            this.maze = new Maze(fileName);
        }catch (Exception e) {

        }
    }

}
