package exceptions;

/**
 * Exception which is thrown when a Maze file's grid content does not match
 * its defined dimensions.
 */
public class MazeSizeMissmatchException extends Exception{

    /**
     * Exception constructor.
     * @param message Message to describe exception
     */
    public MazeSizeMissmatchException(String message){
        super(message);
    }

}
