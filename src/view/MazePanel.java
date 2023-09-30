package view;

import java.awt.*;
import javax.swing.JPanel;

public class MazePanel extends JPanel {

    private final int x, y, width, height, tileWidth;
    private final Color[][] colorMap;

    public MazePanel(int x, int y, int width, int height, int tileWidth, Color[][] colorMap){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.colorMap = colorMap;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Setup
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw color tiles
        if(colorMap != null) {
            for (int x = 0; x < colorMap.length; x++) {
                for (int y = 0; y < colorMap[0].length; y++) {
                    g2d.setColor(colorMap[x][y]);
                    g2d.fillRect(this.x + x * tileWidth, this.y + y * tileWidth, tileWidth, tileWidth);
                }
            }
        }

        /*
        // Display message
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        FontMetrics metrics = g.getFontMetrics();
        int x = (width - metrics.stringWidth(displayText)) / 2;
        int y = height - 63;
        g.drawString(displayText, x, y);
        */
    }

}
