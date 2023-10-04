package models;

/**
 * A tile of a maze.
 */
public class MazeTile {

    /** Reference to instance. **/
    private final String tileID;
    /** Whether this tile can be passed over. **/
    private final boolean isTraversable;

    /**
     * Constructs a MazeTile.
     * @param isTraversable Whether this tile be travelled over.
     * @param tileID The unique name of this tile.
     */
    public MazeTile(String tileID, boolean isTraversable){
        this.isTraversable = isTraversable;
        this.tileID = tileID;
    }

    /**
     * Gets whether the tile can be travelled over.
     * @return Whether the tile is traversable.
     */
    public boolean IsTraversable(){ return isTraversable; }

    /**
     * Gets the unique string ID name of the tile.
     * @return Unique string ID.
     */
    public String getTileID(){ return tileID; }

    /**
     * Gets the unique string ID of the tile.
     * @return Unique string ID.
     */
    @Override
    public String toString() { return tileID;  }
}
