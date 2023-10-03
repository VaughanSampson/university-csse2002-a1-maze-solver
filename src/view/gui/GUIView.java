package view.gui;

import models.Maze;
import view.View;

import java.awt.*;
import javax.swing.*;

public class GUIView implements View {

    private final JFrame frame;

    public GUIView(){
        frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,675);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void generateDisplay(Maze maze, String message){
        JPanel panel;
        if(maze != null) {
            panel = new GUIMazePanel(frame.getWidth(), frame.getHeight(), maze);
        }
        else
        {
            // Create empty panel
            panel = new JPanel();
        }

        // Put text into panel
        panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(label);

        // Render panel
        frame.add(panel);
    }

}
