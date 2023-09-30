package models;

import java.awt.*;

/**
 * Travelled tile component for a maze.
 */
public class MazeTravelled implements MazeComponent {

    /**
     * Get whether a travelled tile can be travelled over.
     * @return True.
     */
    @Override
    public boolean IsTraversable() {
        return true;
    }

    /**
     * Get a color for rendering a travelled tile component.
     * @return The color gray.
     */
    @Override
    public Color getColor() {
        return Color.gray;
    }

    /**
     * String representation for debugging.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Travelled";
    }
}
