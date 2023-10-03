package models;

import java.util.HashMap;

/**
 * Generates and maintains a complete model of a maze.
 */
public class Maze {

    private final int width, height;
    private MazeTile[][] map;
    private Position start, end;
    public static final MazeTile Wall = new MazeTile("wall", false),
            Space = new MazeTile("space", true),
            End = new MazeTile("end", true),
            Start = new MazeTile("start", true),
            Path = new MazeTile("path", false),
            Travelled = new MazeTile("travelled", false);

    public static final HashMap<Character, MazeTile> CharacterToComponentMap = new HashMap<Character, MazeTile>(){{
        put('#', Wall);
        put(' ', Space);
        put('.', Space);
        put('S', Start);
        put('E', End);
    }};

    /**
     * Constructs a maze by reading a file and converting chars into MazeComponent objects
     * to populate a 2D array.
     * @param map Map of chars acting as maze blueprint.
     */
    public Maze(char[][] map) {
        this.width = map.length;
        this.height = map[0].length;
        generateMazeTilesFromCharMap(map);
    }

    /**
     * Converts a 2D array of chars into a 2D array of MazeComponent objects.
     * @param map Char map.
     */
    private void generateMazeTilesFromCharMap(char[][] map) {
        // Create map
        this.map = new MazeTile[this.width][this.height];

        // Populate map
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++)
            {
                MazeTile component = CharacterToComponentMap.get(map[x][y]);
                this.map[x][y] = component;

                // Get start and end
                if(component.getTileID().equals("start")) start = new Position(x, y);
                if(component.getTileID().equals("end")) end = new Position(x, y);
            }
        }
    }

    public MazeTile getTile(int x, int y) {
        if(x >= width || y >= height) {
            throw new IndexOutOfBoundsException("Position (" + x + "," + y + ") is outside bounds the of the maze.");
        }
        return map[x][y];
    }

    /**
     * Gets the component at particular coordinates.
     * @param pos Position of component.
     * @return MazeComponent object at the given position.
     * @throws IndexOutOfBoundsException If coordinates entered are outside the map's bounds.
     */
    public MazeTile getTile(Position pos) {
       return getTile(pos.getX(), pos.getY());
    }

    public void setTile(int x, int y, MazeTile mazeTile) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Position (" + x + "," + y + ") is outside bounds of map.");
        }
        if(!getTile(x, y).getTileID().equals("start") && !getTile(x, y).getTileID().equals("end")) {
            map[x][y] = mazeTile;
        }
    }

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
