package view.gui;

import models.Maze;
import view.View;

import java.awt.*;
import javax.swing.*;

/**
 * Creates and initiates a window GUI view of a Maze.
 */
public class GUIView implements View {

    /** Frame of display. */
    private final JFrame frame;

    /**
     * Constructor initiating the frame of display.
     */
    public GUIView(){
        frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,675);
        frame.setResizable(false);
    }

    /**
     * Generates the maze display onto the frame and makes the frame visible.
     * @param maze Maze to display.
     * @param message Message to show within display.
     */
    @Override
    public void generateDisplay(Maze maze, String message){

        // If there is a maze, create a maze display panel, otherwise make an
        // empty panel.
        JPanel panel;

        if(maze != null) {
            panel = new GUIMazePanel(frame.getWidth(), frame.getHeight(), maze);
        }
        else
        {
            panel = new JPanel();
        }

        // Put a message into the panel
        panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(label);

        // Render everything
        frame.add(panel);
        frame.setVisible(true);
    }

}
