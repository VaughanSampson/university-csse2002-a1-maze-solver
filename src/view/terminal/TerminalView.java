package view.terminal;

import models.Maze;
import view.View;

import java.util.HashMap;

/**
 * Can output a maze view into a terminal.
 */
public class TerminalView implements View {

    /**
     * Allows MazeTiles to be mapped to terminal color tags.
     */
    private static final HashMap<String, String> tileIDToANSIColorMap
            = new HashMap<String, String>(){{
        put("wall", "\u001B[47m");
        put("start", "\u001B[41m");
        put("space", "\u001B[40m");
        put("path", "\u001B[46m");
        put("end", "\u001B[42m");
        put("travelled", "\u001B[44m");
    }};

    /**
     * Outputs a display of a maze to the computer terminal.
     * @param maze Maze to display.
     * @param message Message to show with display.
     */
    @Override
    public void generateDisplay(Maze maze, String message) {
        // Prints message
        System.out.println(message);

        // Ensure maze doesn't print if null
        if(maze == null){
            return;
        }

        // Paints the background color of spaces to display a maze
        for(int y = 0; y < maze.getHeight(); y++){
            for(int x = 0; x < maze.getWidth(); x++) {
                System.out.print(tileIDToANSIColorMap.get(maze.getTile(x, y).getTileID()) + "  ");
            }
            System.out.println("\033[0m");
        }
    }

}
