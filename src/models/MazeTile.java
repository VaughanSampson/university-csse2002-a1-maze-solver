package models;

import java.awt.*;

/**
 * Class for maze components.
 */
public class MazeTile {

    private String tileID;
    private final boolean isTraversable;

    /**
     * Constructs a MazeComponent.
     * @param isTraversable Can this component be travelled over.
     * @param tileID What is this component called.
     */
    public MazeTile(String tileID, boolean isTraversable){
        this.isTraversable = isTraversable;
        this.tileID = tileID;
    }

    public boolean IsTraversable(){ return isTraversable; }

    public String getTileID(){ return tileID; }

    @Override
    public String toString() { return tileID;  }
}
