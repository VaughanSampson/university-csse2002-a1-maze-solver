package exceptions;

/**
 * Exception which is thrown when a Maze file's content is not formatted
 * correctly.
 */
public class MazeMalformedException extends Exception{

    /**
     * Exception constructor.
     * @param message Message to describe exception
     */
    public MazeMalformedException(String message){
        super(message);
    }

}
