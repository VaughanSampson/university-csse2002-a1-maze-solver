package models;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileInterface;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.util.HashMap;

/**
 * Generates and maintains a complete model of a maze.
 */
public class Maze implements FileInterface {

    private final int width, height;
    private MazeComponent[][] map;
    private final HashMap<Character, MazeComponent> characterToComponent = new HashMap<Character, MazeComponent>(){{
        put('#', new MazeWall());
        put('S', new MazeStart());
        put('E', new MazeEnd());
        put(' ', new MazeEmpty());
    }};

    /**
     * Constructs a maze by reading a file and converting chars into MazeComponent objects
     * to populate a 2D array.
     * @param fileName Name of file to read.
     * @throws MazeMalformedException      If the maze file data is not correctly formatted.
     * @throws MazeSizeMissmatchException  If the maze file dimensions do not match the provided size.
     * @throws IllegalArgumentException     If a char in the given maze file does not map to a MazeComponent.
     * @throws FileNotFoundException        If the maze file is not found.
     */
    public Maze(String fileName) throws MazeMalformedException, MazeSizeMissmatchException, IllegalArgumentException, FileNotFoundException {
        char[][] map = load(fileName);
        this.width = map.length;
        this.height = map[0].length;
        populateMapFromCharMap(map);
    }

    /**
     * Converts a 2D array of chars into a 2D array of MazeComponent objects.
     * @param map Char map.
     * @throws IllegalArgumentException If a char in the given char map does not map to a MazeComponent.
     */
    private void populateMapFromCharMap(char[][] map) throws IllegalArgumentException{
        // Create map
        this.map = new MazeComponent[this.width][this.height];

        // Populate map
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++)
            {
                if(!characterToComponent.containsKey(map[x][y]))
                    throw new IllegalArgumentException("Map should not contain symbol " + map[x][y]);

                MazeComponent component = characterToComponent.get(map[x][y]);
                this.map[x][y] = component;
            }
        }
    }

    /**
     * Reads a maze file. First gets its dimensions then iterates through those
     * dimensions to build a 2D character array from the file's data.
     * @param filename The path to the maze file to be loaded.
     * @return A 2D character array representing the loaded maze.
     * @throws MazeMalformedException      If the maze data is not correctly formatted.
     * @throws MazeSizeMissmatchException  If the maze dimensions do not match the provided size
     * @throws IllegalArgumentException    If a char in the maze file's map does not map to any MazeComponent.
     * @throws FileNotFoundException        If the maze file is not found.
     */
    @Override
    public char[][] load(String filename) throws MazeMalformedException, MazeSizeMissmatchException, IllegalArgumentException, FileNotFoundException {

        // Get relative path
        String filePath = new File("").getAbsolutePath();
        String path = filePath+"\\"+filename;

        // Create stream
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Get dimensions
        String dimensionsLine = null;
        try {
            dimensionsLine = bufferedReader.readLine();
        }
        catch (Exception e){
            throw new MazeMalformedException("No text in: " + path);
        }

        if(dimensionsLine == null)
            throw new MazeMalformedException("No text in: " + path);

        String[] dimensions = dimensionsLine.split(" ");
        if(dimensions.length < 2)
            throw new MazeMalformedException("2 dimensions were note defined in: " + path);

        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        // Populate 2D Char array
        char[][] map = new char[width][height];
        for (int y = 0; y < height; y++){

            String line = null;
            try {
                line = bufferedReader.readLine();
            } catch (Exception e){
                throw new MazeSizeMissmatchException( "The Maze is less tall than specified.");
            }

            if(line == null)
                throw new MazeSizeMissmatchException( "The Maze is less tall than specified.");
            if(line.length() < width)
                throw new MazeSizeMissmatchException( "Row " + y + "of the maze is shorter than specified.");

            for(int x = 0; x < width; x++) {
                map[x][y] = line.charAt(x);
                if(!characterToComponent.containsKey(map[x][y]))
                    throw new IllegalArgumentException("Map should not contain symbol " + map[x][y]);
            }
        }

        try {
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e){
            // ignore
        }

        return map;
    }

    public Color[][] getColorMap(){
        Color[][] colorMap = new Color[width][height];
        for(int x = 0; x < width; x ++){
            for(int y = 0; y < height; y ++){
                colorMap[x][y] = map[x][y].getColor();
            }
        }
        return colorMap;
    }

    /**
     * Gets the component at particular coordinates.
     * @param x X coordinate of component.
     * @param y Y coordinate of component.
     * @return MazeComponent object at the given position.
     * @throws IndexOutOfBoundsException If coordinates entered are outside the map's bounds.
     */
    public MazeComponent getComponent(int x, int y) throws IndexOutOfBoundsException {
        if(x < 0 || x >= width || y < 0 || y >= height)
            throw new IndexOutOfBoundsException("Coordinate (" + x + "," + y + ") is outside bounds of map.");
        return map[x][y];
    }

    /**
     * Gets the width of the map.
     * @return Width.
     */
    public int getWidth(){ return  width; }

    /**
     * Gets the height of the map.
     * @return Height.
     */
    public int getHeight(){ return  height; }

}
