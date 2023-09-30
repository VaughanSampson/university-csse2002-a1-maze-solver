package models;

import java.awt.*;

/**
 * Start tile component for a maze.
 */
public class MazeStart implements MazeComponent {

    /**
     * Get whether a start tile can be travelled over.
     * @return True.
     */
    @Override
    public boolean IsTraversable() {
        return true;
    }

    /**
     * Get a color for rendering a start tile component.
     * @return The color red.
     */
    @Override
    public Color getColor() {
        return Color.red;
    }

    /**
     * String representation for debugging.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Start";
    }
}
