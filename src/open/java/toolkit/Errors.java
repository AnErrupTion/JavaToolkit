package open.java.toolkit;

import open.java.toolkit.console.ansi.Foreground;
import open.java.toolkit.files.Files;

import java.util.Arrays;

public class Errors
{
    /**
     * This function should ONLY be used in JavaToolkit's internals, and not anywhere else.
     * @param ex The exception.
     */
    public static void newError(Exception ex)
    {
        System.errorOccurred = true;

        if (System.showErrors)
            java.lang.System.err.println(Foreground.RED_BOLD_BRIGHT + "[JAVATOOLKIT EXCEPTION] " + ex.getMessage() + Foreground.RESET);

        if (System.logErrors)
            Files.writeString("javatoolkit_v" + System.getToolkitVersion() + "_" + System.getDateAndTime("yyyy_MM_dd_HH_mm") + ".log", ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()), true);
    }
}
