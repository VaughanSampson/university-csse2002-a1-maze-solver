package view;

import java.awt.*;
import javax.swing.JPanel;

public class MazeCanvas extends JPanel {

    private final int x, y, width, height, tileWidth;
    private Color[][] colorMap = null;

    public MazeCanvas(int x, int y, int width, int height, int tileWidth){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Setup
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background
        g2d.setColor(Color.lightGray);
        g2d.fillRect(x, y, width, height);

        // Draw color tiles
        if(colorMap != null) {
            for (int x = 0; x < colorMap.length; x++) {
                for (int y = 0; y < colorMap[0].length; y++) {
                    g2d.setColor(colorMap[x][y]);
                    g2d.fillRect(this.x + x * tileWidth, this.y + y * tileWidth, tileWidth, tileWidth);
                }
            }
        }
    }

    public void SetColorMap(Color[][] colorMap) {
        this.colorMap = colorMap;
    }
}
