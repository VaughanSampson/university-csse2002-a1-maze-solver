package view;

import java.awt.*;
import javax.swing.JFrame;

public class MasterFrame {

    private final JFrame frame;
    private MazeCanvas canvas;

    public MasterFrame(){
        frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,675);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void createMazeColorMap(Color[][] colorMap){
        if(canvas != null) {
            frame.remove(canvas);
        }

        // Variables to centre maze visual
        int yMargin = 100;
        int tileWidth = (frame.getHeight()-(yMargin*2))/colorMap[0].length;
        int xMargin = (frame.getWidth() - (tileWidth * colorMap.length))/2;

        canvas = new MazeCanvas(xMargin, yMargin, frame.getWidth(), frame.getHeight(), tileWidth, colorMap);
        frame.add(canvas);
    }


}
