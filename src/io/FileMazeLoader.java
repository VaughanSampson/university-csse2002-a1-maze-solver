package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import models.Maze;

import java.io.*;

/**
 * Class utilised to generate a 2D maze char map from a file.
 */
public class FileMazeLoader implements FileInterface {

    /**
     * Reads a maze file to create a 2D char array representing a maze
     * structure. First it reads the maze's dimensions then iterates those
     * dimensions to read and build a 2D character array from the file's data.
     * @param path The path to the maze file to be loaded.
     * @return A 2D char array representing the loaded maze.
     * @throws MazeMalformedException If the maze data is not correctly
     * formatted.
     * @throws MazeSizeMissmatchException If the maze dimensions do not match
     * the provided size.
     * @throws IllegalArgumentException If a dimension given is
     * not a digit or if a character in the maze file does not
     * map to any valid MazeTile object.
     * @throws FileNotFoundException If the maze file is not found.
     */
    @Override
    public char[][] load(String path) throws MazeMalformedException,
            MazeSizeMissmatchException, IllegalArgumentException,
            FileNotFoundException {

        // Create the Map
        char[][] map = null;


        // All reading operations are wrapped in this try catch to ensure
        // the readers will close even if an exception is thrown
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {


            String dimensionsLine = null;
            // Read first line for dimensions
            dimensionsLine = bufferedReader.readLine();
            // Throw exception if there is no content in file
            if (dimensionsLine == null) {
                throw new MazeMalformedException("No text in file.");
            }

            // Split line to separate the two expected dimensions
            String[] dimensions = dimensionsLine.split(" ");

            // Throw exceptions if there are not enough dimensions
            if (dimensions.length < 2) {
                throw new MazeMalformedException("2 dimensions were not " +
                        "defined");
            }

            // Parse dimension strings into integers, throwing an exception
            // if they are not in digit format.
            int height, width;
            try{
                height = Integer.parseInt(dimensions[0]);
                width = Integer.parseInt(dimensions[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Dimensions are not " +
                        "correctly defined as digits.");
            }

            // Throw an exception if dimensions are not even
            if(height % 2 != 1 || width % 2 != 1) {
                throw new MazeMalformedException("At least one dimension of " +
                        "the maze was even. They must both be be odd.");
            }

            // Create counters to ensure only 1 start and 1 end are defined
            int startPointCount = 0, endPointCount = 0;

            // Populate 2D char array
            map = new char[width][height];
            for (int y = 0; y < height; y++) {

                String line = null;
                line = bufferedReader.readLine();

                // Throw exceptions if there is not the structure of characters
                // in the grid, as defined by the dimensions.
                if (line == null)
                    throw new MazeSizeMissmatchException("The grid is less " +
                            "tall than specified.");
                if (line.length() < width)
                    throw new MazeSizeMissmatchException("Row " + y + " of " +
                            "the grid is shorter than specified.");

                for (int x = 0; x < width; x++) {
                    // Throw exception if a character read does not map to
                    // a maze tile.
                    if (!Maze.CharacterToComponentMap
                            .containsKey(line.charAt(x))) {

                        throw new IllegalArgumentException("The file " +
                                "contained the symbol '" + map[x][y]
                                + "' which does not map to any maze tile.");
                    }

                    // Add to start and end counters when start or end is read
                    if (line.charAt(x) == 'E') endPointCount++;
                    if (line.charAt(x) == 'S') startPointCount++;

                    map[x][y] = line.charAt(x);
                }
            }

            // Throw exception if there is not exactly 1 start and 1 end
            if (startPointCount != 1 || endPointCount != 1)
                throw new MazeMalformedException("There was not 1 start" +
                        " and 1 end in the maze.");

        } catch (FileNotFoundException e) {
            // Rethrow exception with altered message and simpler stack trace
            throw new FileNotFoundException("The target file is not at the" +
                    " location specified.");
        }
        catch (IOException e) {
            // Handle IOExceptions here as throwing it is not part of the
            // parent interface which cannot be altered.
            e.printStackTrace();
        }

        // Return map
        return map;
    }
}
