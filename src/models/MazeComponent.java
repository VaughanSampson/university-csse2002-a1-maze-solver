package models;

import java.awt.*;

/**
 * Class for maze components.
 */
public class MazeComponent {

    private String name;
    private final boolean isTraversable;
    private final Color color;

    /**
     * Constructs a MazeComponent.
     * @param isTraversable Can this component be travelled over.
     * @param color The color to represent this component.
     * @param name What is this component called.
     */
    public MazeComponent(boolean isTraversable, Color color, String name){
        this.isTraversable = isTraversable;
        this.color = color;
        this.name = name;
    }

    public Color getColor(){ return color; }
    public boolean IsTraversable(){ return isTraversable; }

    public String getName(){ return name; }

}
