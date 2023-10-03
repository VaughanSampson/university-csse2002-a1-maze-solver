package io;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import models.Maze;

import java.io.*;

/**
 * Generates and maintains a complete model of a maze.
 */
public class FileMazeLoader implements FileInterface {

    /**
     * Reads a maze file. First gets its dimensions then iterates through those
     * dimensions to build a 2D character array from the file's data.
     * @param path The path to the maze file to be loaded.
     * @return A 2D character array representing the loaded maze.
     * @throws MazeMalformedException      If the maze data is not correctly formatted.
     * @throws MazeSizeMissmatchException  If the maze dimensions do not match the provided size
     * @throws IllegalArgumentException    If a char in the maze file's map does not map to any MazeComponent.
     * @throws FileNotFoundException        If the maze file is not found.
     */
    @Override
    public char[][] load(String path) throws MazeMalformedException, MazeSizeMissmatchException, IllegalArgumentException, FileNotFoundException {

        //Create Map
        char[][] map = null;


        // All reading operations are wrapped in this try catch to ensure the readers will close even
        // when an exception is thrown
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            // Get dimensions
            String dimensionsLine = null;
            dimensionsLine = bufferedReader.readLine();

            if (dimensionsLine == null) {
                throw new MazeMalformedException("No text in maze file.");
            }

            String[] dimensions = dimensionsLine.split(" ");

            if (dimensions.length < 2) {
                throw new MazeMalformedException("2 dimensions were not defined in the maze.");
            }

            int height, width;

            try{
                height = Integer.parseInt(dimensions[0]);
                width = Integer.parseInt(dimensions[1]);
            } catch (NumberFormatException e) {
                throw new MazeMalformedException("First two words were not digits. " +
                        "Dimensions are not correctly defined.");
            }

            if(height % 2 != 1 || width % 2 != 1) {
                throw new MazeMalformedException("At least one dimension of the maze was even. " +
                        "They must both be be odd.");
            }

            int startPointCount = 0, endPointCount = 0;

            // Populate 2D Char array
            map = new char[width][height];
            for (int y = 0; y < height; y++) {

                String line = null;
                line = bufferedReader.readLine();

                if (line == null)
                    throw new MazeSizeMissmatchException("The Maze is less tall than specified.");
                if (line.length() < width)
                    throw new MazeSizeMissmatchException("Row " + y + " of the maze is shorter than specified.");

                for (int x = 0; x < width; x++) {

                    if (!Maze.CharacterToComponentMap.containsKey(line.charAt(x)))
                        throw new IllegalArgumentException(  "The maze should not contain the symbol '"
                                + map[x][y] + "'");

                    if (line.charAt(x) == 'E') endPointCount++;
                    if (line.charAt(x) == 'S') startPointCount++;

                    map[x][y] = line.charAt(x);
                }
            }

            // Check that there was is one start and one end
            if (startPointCount != 1 || endPointCount != 1)
                throw new MazeMalformedException("There was not 1 start and 1 end in the maze.");

        } catch (FileNotFoundException e) {
            // Throw exception with altered message and simpler stack trace
            throw new FileNotFoundException("The target file is not at the location specified.");
        }
        catch (IOException e) {
            // Handle exception here as it is not part of the parent interface which cannot be altered
            e.printStackTrace();
        }

        return map;
    }

}
