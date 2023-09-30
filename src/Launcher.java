import  controllers.MainController;
public class Launcher {
    public static void main(String[] args) {

        String path = null;
        boolean usesGUI = false;

        for(String s : args){
            if(s.equals("GUI"))
                usesGUI = true;
            else
             if(s.endsWith(".txt"))
                path = s;
        }

        if(path == null)
            throw new IllegalArgumentException("A maze file's name must be entered as a program argument on compile " +
                    "and end in .txt");

        if(!usesGUI)
            throw new UnsupportedOperationException("A command line application is not implemented. Enter 'GUI' as a " +
                    "program argument to run the GUI implementation.");

        MainController controller = new MainController(path);
    }
}