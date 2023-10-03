import  controllers.MainController;
public class Launcher {
    public static void main(String[] args) {

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

        if(mazePath == null) {
            throw new IllegalArgumentException("A maze file's name ending in '.txt' must be entered as a program " +
                    "argument on compile.");
        }

        MainController.solveAndDisplayMaze(mazePath, usesGUI);
    }
}