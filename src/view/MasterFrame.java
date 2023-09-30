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

    public void beginNewMazeCanvas(){
        if(canvas != null) {
            frame.remove(canvas);
        }

        canvas = new MazeCanvas(0, 50, frame.getWidth(), frame.getHeight()-150, 20);
        frame.add(canvas);
    }
    public void setMazeColorMap(Color[][] colorMap){
        canvas.SetColorMap(colorMap);
    }


}
