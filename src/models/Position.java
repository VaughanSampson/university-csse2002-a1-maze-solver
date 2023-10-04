package models;

/**
 * Stores an (x, y) position.
 */
public class Position {

    /** An axis component of the position. */
    private final int x, y;

    /**
     * Constructs position.
     * @param x X-axis component of position.
     * @param y Y-axis component of position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X-axis component of the position.
     * @return x.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y-axis component of the position.
     * @return y.
     */
    public int getY() {
        return y;
    }

    /**
     * Compares this position with another object for equality.
     * @param obj Other object.
     * @return True if the object is a position with the same x and
     * y components. Otherwise, false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position) {
            return (x == ((Position) obj).getX() && y == ((Position) obj).getY());
        }
        else {
            return false;
        }
    }

    /**
     * Create a position which is the vector addition of this position and
     * another position.
     * @param otherPosition Position to add to this position.
     * @return The position resulting from the addition.
     */
    public Position add(Position otherPosition){
        return new Position(this.x + otherPosition.getX(), this.y + otherPosition.getY());
    }

    /**
     * Get the string representation of the position.
     * @return The string representation of the position.
     */
    @Override
    public String toString() {
        return "Position (" + x + ", " + y + ")";
    }
}
