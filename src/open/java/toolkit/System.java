package open.java.toolkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class System
{
    public static final String newLine = System.isWindows() ? "\r\n" : "\n";
    public static final String toolkitVersion = "1.4";
    public static final String dateAndTime = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());

    public static boolean logErrors = false, errorOccured = false, showErrors = true;

    public static boolean getPropertyContains(String property, String element, boolean lowerCase)
    {
        String prop = java.lang.System.getProperty(property);
        if (lowerCase) prop = prop.toLowerCase();
        return prop.contains(element);
    }

    public static boolean isWindows()
    {
        return getPropertyContains("os.name", "windows", true);
    }
}
