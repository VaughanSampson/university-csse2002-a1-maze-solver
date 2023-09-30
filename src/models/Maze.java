package models;

import exceptions.MazeMalformedException;
import exceptions.MazeSizeMissmatchException;
import io.FileInterface;

import java.io.*;

public class Maze implements FileInterface {
    public Maze(String fileName){
        char[][] map;
        try {
            map = load(fileName);
        } catch (Exception e)
        {

        }
    }

    /**
     * Reads a maze file. First gets its dimensions then iterates through those
     * dimensions to build a 2D character array from the file's data.
     * @param filename The path to the maze file to be loaded.
     * @return A 2D character array representing the loaded maze.
     * @throws MazeMalformedException      If the maze data is not correctly formatted.
     * @throws MazeSizeMissmatchException  If the maze dimensions do not match the provided size.
     * @throws IllegalArgumentException     For other validation errors.
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

            for(int x = 0; x < width; x++)
                map[x][y] = line.charAt(x);
        }

        try {
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e){
            // ignore
        }

        return map;
    }
}
