package models;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileInterface;
import javafx.geometry.Pos;

import java.awt.*;
import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Generates and maintains a complete model of a maze.
 */
public class Maze implements FileInterface {

    private final int width, height;
    private MazeComponent[][] map;
    private Position start, end;

    private final HashMap<Character, MazeComponent> characterToComponent = new HashMap<Character, MazeComponent>(){{
        put('#', new MazeComponent(false, Color.black, "wall"));
        put('S', new MazeComponent(false, Color.red, "start"));
        put('E', new MazeComponent(true, Color.green, "end"));
        put(' ', new MazeComponent(true, Color.gray, "space"));
    }};

    private BufferedReader bufferedReader;

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
    private void populateMapFromCharMap(char[][] map) throws IllegalArgumentException, MazeMalformedException{
        // Create map
        this.map = new MazeComponent[this.width][this.height];

        // Populate map
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++)
            {
                if(!characterToComponent.containsKey(map[x][y]))
                    throw new IllegalArgumentException("Map should not contain symbol " + map[x][y]);

                MazeComponent component = characterToComponent.get(map[x][y]);

                // Get start and end
                if(component.getName().equals("start")) {
                    if(start != null)
                        throw new MazeMalformedException("Multiple starts defined in map.");
                    start = new Position(x, y);
                }

                if(component.getName().equals("end")) {
                    if(end != null)
                        throw new MazeMalformedException("Multiple ends defined in map.");
                    end = new Position(x, y);
                }

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

        //Create Map
        char[][] map = null;

        // Get relative path
        String filePath = new File("").getAbsolutePath();
        String path = filePath+"\\"+filename;

        // Create stream
        FileReader fileReader = new FileReader(path);

        // All reading operations are wrapped in this try catch to ensure the buffered reader will close even
        // when an exception is thrown
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            // Get dimensions
            String dimensionsLine = null;
            try {
                dimensionsLine = bufferedReader.readLine();
            } catch (Exception e) {
                throw new MazeMalformedException("No text in: " + path);
            }

            if (dimensionsLine == null)
                throw new MazeMalformedException("No text in: " + path);

            String[] dimensions = dimensionsLine.split(" ");
            if (dimensions.length < 2)
                throw new MazeMalformedException("2 dimensions were note defined in: " + path);

            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);

            int startPointCount = 0, endPointCount = 0;

            // Populate 2D Char array
            map = new char[width][height];
            for (int y = 0; y < height; y++) {

                String line = null;
                try {
                    line = bufferedReader.readLine();
                } catch (Exception e) {
                    throw new MazeSizeMissmatchException("The Maze is less tall than specified.");
                }

                if (line == null)
                    throw new MazeSizeMissmatchException("The Maze is less tall than specified.");
                if (line.length() < width)
                    throw new MazeSizeMissmatchException("Row " + y + "of the maze is shorter than specified.");

                for (int x = 0; x < width; x++) {

                    if (!characterToComponent.containsKey(line.charAt(x)))
                        throw new IllegalArgumentException("Map should not contain symbol " + map[x][y]);
                    if (line.charAt(x) == 'E') endPointCount++;
                    if (line.charAt(x) == 'S') startPointCount++;

                    map[x][y] = line.charAt(x);
                }
            }

            // Ensure there was only one start and one end
            if (startPointCount != 1 || endPointCount != 1)
                throw new MazeMalformedException("There was not 1 start and 1 end in the maze.");

        } catch (IOException e) {
            // HANDLE
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
     * @param pos Position of component.
     * @return MazeComponent object at the given position.
     * @throws IndexOutOfBoundsException If coordinates entered are outside the map's bounds.
     */
    public MazeComponent getComponent(Position pos) throws IndexOutOfBoundsException {
        if(pos.getX() >= width || pos.getY() >= height)
            throw new IndexOutOfBoundsException("Position (" + pos.getX() + "," + pos.getY() + ") is outside bounds of map.");
        return map[pos.getX()][pos.getY()];
    }

    public void setComponent(Position pos, MazeComponent mazeComponent)throws IndexOutOfBoundsException {
        if (pos.getX() < 0 || pos.getX() >= width || pos.getY() < 0 || pos.getY() >= height)
            throw new IndexOutOfBoundsException("Position (" + pos.getX() + "," + pos.getY() + ") is outside bounds of map.");
        if(!getComponent(pos).getName().equals("start") && !getComponent(pos).getName().equals("end"))
            map[pos.getX()][pos.getY()] = mazeComponent;
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
