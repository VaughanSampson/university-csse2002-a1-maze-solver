package models;

import java.awt.*;

/**
 * End tile component for a maze.
 */
public class MazeEnd implements MazeComponent {

    /**
     * Get whether an end tile can be travelled over.
     * @return True.
     */
    @Override
    public boolean IsTraversable() {
        return true;
    }

    /**
     * Get a color for rendering an end tile component.
     * @return The color green.
     */
    @Override
    public Color getColor() {
        return Color.green;
    }

    /**
     * String representation for debugging.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "End";
    }
}
