package open.java.toolkit;
import open.java.toolkit.console.ansi.Foreground;
import open.java.toolkit.files.Files;
public class Errors
{
    // Should be invoked ONLY when an exception occurs in JavaToolkit.
    public static void newError(String message)
    {
        System.errorOccured = true;
        java.lang.System.err.println(Foreground.RED_BOLD_BRIGHT + "[JAVATOOLKIT EXCEPTION] " + message);
        if (System.logErrors)
            Files.writeString("javatoolkit_v" + System.toolkitVersion + "_" + System.dateAndTime + ".log", message, true);
    }
}
