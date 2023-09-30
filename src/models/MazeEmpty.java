package models;

import java.awt.*;

/**
 * Empty space component for a maze.
 */
public class MazeEmpty implements MazeComponent {

    /**
     * Get whether this component be travelled over.
     * @return False.
     */
    @Override
    public boolean IsTraversable() {
        return true;
    }

    /**
     * Get a color for rendering this component.
     * @return Null.
     */
    @Override
    public Color getColor() { return null; }

    /**
     * String representation for debugging.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Empty";
    }
}
