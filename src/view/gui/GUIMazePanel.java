package view.gui;

import models.Maze;
import models.MazeTile;
import models.Position;

import java.awt.*;
import java.util.HashMap;
import javax.swing.JPanel;

public class GUIMazePanel extends JPanel {

    private final int x, y, width, height, tileWidth;
    private final Maze maze;

    private static final HashMap<String, Color> tileIDToColorMap = new HashMap<String, Color>(){{
        put("wall", Color.black);
        put("start", Color.red);
        put("end", Color.green);
        put("space", Color.gray);
        put("path", Color.cyan);
        put("travelled", Color.blue);
    }};

    public GUIMazePanel(int width, int height, Maze maze){
        super();
        this.width = width;
        this.height = height;
        y = 100;
        tileWidth = (height - (y * 2)) / maze.getHeight();
        x = (width - (tileWidth * maze.getWidth())) / 2;
        this.maze = maze;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Setup
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw color tiles
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                g2d.setColor(tileIDToColorMap.get(maze.getTile(x,y).getTileID()));
                g2d.fillRect(this.x + x * tileWidth, this.y + y * tileWidth, tileWidth, tileWidth);
            }
        }

    }

}
