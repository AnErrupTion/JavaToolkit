package open.java.toolkit.console;

public class Console
{
    public static void writeLine(String text, LogType type)
    {
        write(text + "\n", type);
    }

    public static void write(String text, LogType type)
    {
        System.out.printf("[%s] %s", type.toString(), text);
    }

    public static void colorWriteLine(String ansiColorStart, String text, LogType type)
    {
        colorWrite(ansiColorStart,text + "\n", type);
    }

    public static void colorWrite(String ansiColorStart, String text, LogType type)
    {
        System.out.printf(ansiColorStart + "[%s] %s", type.toString(), text);
    }
}
