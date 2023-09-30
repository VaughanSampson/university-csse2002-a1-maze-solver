package models;

import java.awt.*;

/**
 * Wall component for a maze.
 */
public class MazeWall implements MazeComponent {

    /**
     * Get whether a wall can be travelled over.
     * @return False.
     */
    @Override
    public boolean IsTraversable() {
        return false;
    }

    /**
     * Get a color for rendering a wall component.
     * @return The color white.
     */
    @Override
    public Color getColor() { return Color.white; }
}
