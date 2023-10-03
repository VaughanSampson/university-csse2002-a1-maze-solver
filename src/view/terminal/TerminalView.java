package view.terminal;

import models.Maze;
import view.View;

import java.util.HashMap;

public class TerminalView implements View {

    private static final HashMap<String, String> tileIDToANSIColorMap = new HashMap<String, String>(){{
        put("wall", "\u001B[47m");
        put("start", "\u001B[41m");
        put("space", "\u001B[40m");
        put("path", "\u001B[46m");
        put("end", "\u001B[42m");
        put("travelled", "\u001B[44m");
    }};

    @Override
    public void generateDisplay(Maze maze, String message) {
        System.out.println("\u001B[40m"+message);
        for(int y = 0; y < maze.getHeight(); y++){
            for(int x = 0; x < maze.getWidth(); x++)
                System.out.print( tileIDToANSIColorMap.get(maze.getTile(x,y).getTileID()) + "  ");
            System.out.print("\u001B[40m\n");
        }
    }

}
