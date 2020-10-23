package open.java.toolkit;

public class System
{
    public static String getProperty(String property)
    {
        return java.lang.System.getProperty(property);
    }

    public static boolean isWindows()
    {
        return getProperty("os.name").toLowerCase().contains("windows");
    }
}
