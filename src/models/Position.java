package models;

/**
 * Stores an (x, y) position that can be retrieved.
 */
public class Position {

    private final int x, y;

    /**
     * Constructs postion.
     * @param x X-axis position value.
     * @param y Y-axis position value.
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets X-axis position.
     * @return x.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets Y-axis position.
     * @return y.
     */
    public int getY() {
        return y;
    }
}
