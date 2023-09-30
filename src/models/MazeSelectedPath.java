package models;

import java.awt.*;

/**
 * Selected path tile component for a maze.
 */
public class MazeSelectedPath implements MazeComponent {

    /**
     * Get whether a selected path tile can be travelled over.
     * This is always false because a path travelling over itself
     * means it is necessarily an inefficient route.
     * @return False.
     */
    @Override
    public boolean IsTraversable() {
        return false;
    }

    /**
     * Get a color for rendering a selected path tile component.
     * @return The color blue.
     */
    @Override
    public Color getColor() {
        return Color.blue;
    }


    /**
     * String representation for debugging.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Selected Path";
    }
}
