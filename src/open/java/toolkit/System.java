package open.java.toolkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class System
{
    public static final String newLine = System.isWindows() ? "\r\n" : "\n";
    public static final String toolkitVersion = "1.4";
    
    public static boolean logErrors = false, errorOccurred = false, showErrors = true;
    
    public static String getDateAndTime()
    {
        return DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());
    }

    public static boolean isWindows()
    {
        return java.lang.System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows");
    }
}
