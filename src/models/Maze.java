package models;

import java.util.HashMap;

/**
 * A model of a maze.
 */
public class Maze {

    /** A dimension of the maze. */
    private final int width, height;
    /** 2D array containing maze structure. */
    private MazeTile[][] map;
    /** A special position in the maze. */
    private Position start, end;

    /** A tile representing a wall. */
    public static final MazeTile Wall = new MazeTile("wall", false);
    /** A tile representing an empty area. */
    public static final MazeTile Space = new MazeTile("space", true);
    /** A tile representing the solution end point of the maze. */
    public static final MazeTile End = new MazeTile("end", true);
    /** A tile representing the start point of the maze. */
    public static final MazeTile Start = new MazeTile("start",true);
    /** A tile representing a point along the path which completes the maze. */
    public static final MazeTile Path = new MazeTile("path", false);
    /** A tile representing a point travelled which is not on a path to
     * complete the maze. */
    public static final MazeTile Travelled = new MazeTile("travelled", false);

    /** A map used to convert a 2D char array into an array of MazeTile
     * objects **/
    public static final HashMap<Character, MazeTile> CharacterToComponentMap
            = new HashMap<Character, MazeTile>(){{
        put('#', Wall);
        put(' ', Space);
        put('.', Space);
        put('S', Start);
        put('E', End);
    }};

    /**
     * Constructor which builds a maze's content from a 2D char array.
     * @param map A map of chars acting as a blueprint to build the maze.
     */
    public Maze(char[][] map) {
        this.width = map.length;
        this.height = map[0].length;
        generateMazeTilesFromCharMap(map);
    }

    /**
     * Generates the maze's 2D MazeTile array.
     * @param map A 2D char array acting as a blueprint for the maze.
     */
    private void generateMazeTilesFromCharMap(char[][] map) {
        // Create a MazeTile map of the same size as the char map
        this.map = new MazeTile[this.width][this.height];

        // Populate the map
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++)
            {
                // Map a char to a MazeTile
                MazeTile component = CharacterToComponentMap.get(map[x][y]);
                this.map[x][y] = component;

                // Get start
                if(component.getTileID().equals("start")) {
                    start = new Position(x, y);
                }
                // Get end
                if(component.getTileID().equals("end")) {
                    end = new Position(x, y);
                }
            }
        }
    }

    /**
     * Gets the tile from a particular position.
     * @param x X-axis position of tile.
     * @param y Y-axis position of tile.
     * @return The tile at the given position.
     */
    public MazeTile getTile(int x, int y) {
        if(x >= width || y >= height) {
            throw new IndexOutOfBoundsException("Position (" + x + "," + y + ") is outside bounds the of the maze.");
        }
        return map[x][y];
    }

    /**
     * Gets the tile from a particular position.
     * @param position Position of the tile.
     * @return The tile at the given position.
     */
    public MazeTile getTile(Position position) {
       return getTile(position.getX(), position.getY());
    }

    /**
     * Sets the tile at a particular position.
     * @param x X-axis position of tile.
     * @param y Y-axis position of tile.
     * @param mazeTile MazeTile instance to place at the position.
     */
    public void setTile(int x, int y, MazeTile mazeTile) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Position (" + x + "," + y + ") is outside bounds of map.");
        }
        if(!getTile(x, y).getTileID().equals("start") && !getTile(x, y).getTileID().equals("end")) {
            map[x][y] = mazeTile;
        }
    }

    /**
     * Sets the tile at a particular position.
     * @param pos Position of tile.
     * @param mazeTile MazeTile instance to place at the position.
     */
    public void setTile(Position pos, MazeTile mazeTile) {
        setTile(pos.getX(), pos.getY(), mazeTile);
    }

    /**
     * Gets the width of the map.
     * @return Width.
     */
    public int getWidth(){ return width; }

    /**
     * Gets the height of the map.
     * @return Height.
     */
    public int getHeight(){ return height; }

    /**
     * Gets the start position of the Maze.
     * @return The start position of the maze.
     */
    public Position getStart(){
        return start;
    }

    /**
     * Gets the end position of the Maze.
     * @return The end position of the maze.
     */
    public Position getEnd(){
        return end;
    }

}
