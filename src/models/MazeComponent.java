package models;

import java.awt.*;

/**
 * Interface for maze component.
 */
public interface MazeComponent {

    /**
     * Get whether this component can be travelled over.
     */
    boolean IsTraversable();

    /**
     * Get a color for rendering a maze component as a square.
     */
    Color getColor();

}
