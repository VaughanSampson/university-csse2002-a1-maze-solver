package view.gui;

import models.Maze;

import java.awt.*;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 * Paints a maze into a parent JFrame.
 */
public class GUIMazePanel extends JPanel {

    /** Display attribute of maze. */
    private final int x, y, tileWidth;

    /** Maze to display. */
    private final Maze maze;

    /** Maps MazeTiles to Colors for display. */
    private static final HashMap<String, Color> tileIDToColorMap
            = new HashMap<String, Color>(){{
        put("wall", Color.black);
        put("start", Color.red);
        put("end", Color.green);
        put("space", Color.gray);
        put("path", Color.cyan);
        put("travelled", Color.blue);
    }};

    /**
     * Constructor defining display attributes.
     * @param width The width of parent GUI object.
     * @param height The height of parent GUI object.
     * @param maze The maze to display.
     */
    public GUIMazePanel(int width, int height, Maze maze){
        super();
        y = 100;
        tileWidth = (height - (y * 2)) / maze.getHeight();
        x = (width - (tileWidth * maze.getWidth())) / 2;
        this.maze = maze;
    }

    /**
     * Paints the maze.
     * @param g The Graphics object used to paint.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Setup graphics
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw color tiles
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                // Set color by mapping tileID to Color
                g2d.setColor(tileIDToColorMap.get(maze.getTile(x,y).getTileID()));
                // Fill a square area based on the coordinates of the tile
                g2d.fillRect(this.x + x * tileWidth, this.y + y * tileWidth,
                        tileWidth, tileWidth);
            }
        }

    }

}
