package controllers;

import models.Maze;
import models.MazeComponent;
import models.Position;
import view.MasterFrame;

import java.awt.*;
import java.util.ArrayList;

public class MainController {

    private Maze maze;
    private MasterFrame masterView;

    private final Position[] possibleMovements = {
            new Position(1,0),
            new Position(0,-1),
            new Position(0,1),
            new Position(-1,0)
    };

    public MainController(){
        masterView = new MasterFrame();
        maze = LoadMap("medium.txt");
        assert maze != null;
        findAndDrawPathToEndpoint(maze.getStart());
        masterView.createMazeColorMap(maze.getColorMap());
    }

    private static Maze LoadMap(String fileName){
        try {
            return new Maze(fileName);
        } catch (Exception e) {
            // DEAL WITH EXCEPTION
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Position> findAndDrawPathToEndpoint(Position currentPosition){
        ArrayList<Position> list = new ArrayList<>();
        list.add(currentPosition);


        if(!currentPosition.equals(maze.getEnd()))
        {
            maze.setComponent(currentPosition, new MazeComponent(false, Color.blue, "travelled"));

            for(Position movement : possibleMovements){
                Position attemptMove = currentPosition.add(movement);
                if(maze.getComponent(attemptMove).IsTraversable())
                {
                    ArrayList<Position> subList = findAndDrawPathToEndpoint(attemptMove);
                    if(subList != null)
                    {
                        maze.setComponent(currentPosition, new MazeComponent(false, Color.cyan, "path"));
                        list.addAll(subList);
                        return list;
                    }
                }
            }
            return null;
        }

        return list;
    }
}
