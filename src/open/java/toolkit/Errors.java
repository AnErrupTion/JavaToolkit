package open.java.toolkit;

import open.java.toolkit.console.ansi.Foreground;

public class Errors
{
    // Should be invoked ONLY when an exception occurs in JavaToolkit.
    public static void newError(String message)
    {
        java.lang.System.err.println(Foreground.RED_BOLD_BRIGHT + "[JAVATOOLKIT EXCEPTION] " + message);
    }
}
