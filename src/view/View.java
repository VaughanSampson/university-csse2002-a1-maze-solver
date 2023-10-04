package view;

import models.Maze;

/**
 * Interface for a maze display.
 */
public interface View {

    /**
     * Function to implement for displaying a maze in a view.
     * @param maze Maze to display.
     * @param message Message to show with display.
     */
    void generateDisplay(Maze maze, String message);
}
