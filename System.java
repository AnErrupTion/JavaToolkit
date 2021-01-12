package open.java.toolkit;

public class System
{
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
