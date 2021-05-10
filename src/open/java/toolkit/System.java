package open.java.toolkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class System
{
    public static final String newLine = System.isWindows() ? "\r\n" : "\n";
    public static boolean logErrors = false, errorOccurred = false, showErrors = true;

    /**
     * Gets the date and time formatted as a string.
     * @param format The format of the date and time string.
     * @return The formatted date and time string.
     */
    public static String getDateAndTime(String format)
    {
        return DateTimeFormatter.ofPattern(format).format(LocalDateTime.now());
    }

    /**
     * Gets the current JavaToolkit version.
     * @return The current JavaToolkit version.
     */
    public static String getToolkitVersion()
    {
        return "1.5";
    }

    /**
     * Checks if the current operating system is *nix-based or Windows-based.
     * @return Whether or not the current operating system is *nix-based or Windows-based.
     */
    public static boolean isWindows()
    {
        return java.lang.System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows");
    }
}
