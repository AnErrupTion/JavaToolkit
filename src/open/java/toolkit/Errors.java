package open.java.toolkit;

import open.java.toolkit.console.ansi.Foreground;
import open.java.toolkit.files.Files;

import java.util.Arrays;

public class Errors
{
    // Should be invoked ONLY when an exception occurs in JavaToolkit.
    public static void newError(Exception ex)
    {
        System.errorOccurred = true;
        
        if (System.showErrors)
            java.lang.System.err.println(Foreground.RED_BOLD_BRIGHT + "[JAVATOOLKIT EXCEPTION] " + ex.getMessage() + Foreground.RESET);
        
        if (System.logErrors)
            Files.writeString("javatoolkit_v" + System.toolkitVersion + "_" + System.dateAndTime + ".log", ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()), true);
    }
}
