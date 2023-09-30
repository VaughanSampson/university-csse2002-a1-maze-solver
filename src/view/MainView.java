package view;

import java.awt.*;
import javax.swing.*;

public class MainView {

    private final JFrame frame;
    private JPanel panel;
    private JLabel label;

    public MainView(){
        frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,675);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void createNewDisplay(Color[][] colorMap, String message){
        //
        if(label != null) { panel.remove(label); }
        if(panel != null) { frame.remove(panel); }

        if(colorMap != null) {
            // Create maze panel
            int yMargin = 100;
            int tileWidth = (frame.getHeight() - (yMargin * 2)) / colorMap[0].length;
            int xMargin = (frame.getWidth() - (tileWidth * colorMap.length)) / 2;
            panel = new MazePanel(xMargin, yMargin, frame.getWidth(), frame.getHeight(), tileWidth, colorMap);
        }
        else
        {
            // Create empty panel
            panel = new JPanel();
        }

        // Put text into panel
        panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        label = new JLabel(message);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(label);

        // Render panel
        frame.add(panel);
    }

}
