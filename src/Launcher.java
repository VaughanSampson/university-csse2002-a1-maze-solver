import  controllers.MainController;

import java.io.File;

/**
 * Initiates the program.
 */
public class Launcher {

    /**
     * Initiates the program utilising program arguments to define
     * functionality.
     * @param args Program arguments.
     */
    public static void main(String[] args) {

        // Gets useful data from program arguments
        String mazePath = null;
        boolean usesGUI = false;
        for(String s : args){
            if(s.equals("GUI")) {
                usesGUI = true;
            }
            else {
                if (s.endsWith(".txt")) {
                    mazePath = s;
                }
            }
        }

        // Throws exception if there was no valid maze file path program argument
        if(mazePath == null) {
            throw new IllegalArgumentException("A maze file's name ending in " +
                    "'.txt' must be entered as a program argument on compile.");
        }
        // Creates complete path to maze file to load
        String fileSeparator = System.getProperty("file.separator");
        String filePath = new File("").getAbsolutePath()
                + fileSeparator + "mazes" + fileSeparator + mazePath;

        // Calls the MainController to load, solve and display the maze
        MainController.solveAndDisplayMaze(filePath, usesGUI);
    }
}