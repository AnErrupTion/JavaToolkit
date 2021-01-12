package open.java.toolkit.console;

import open.java.toolkit.System;
import open.java.toolkit.console.ansi.Foreground;

import java.io.*;

public class Console
{
    public static boolean showAnsiWarning = true;

    public static final PrintStream SOUT = java.lang.System.out;
    public static final InputStream SIN = java.lang.System.in;

    public static void writeLine(String text, LogType type)
    {
        write(text + "\n", type);
    }

    public static void write(String text, LogType type)
    {
        SOUT.printf("[%s] %s", type.toString(), text);
    }

    public static void ansiWriteLine(String ansiStart, String text, LogType type)
    {
        ansiWrite(ansiStart,text + "\r\n", type);
    }

    public static void ansiWrite(String ansiStart, String text, LogType type)
    {
        if (System.isWindows() && showAnsiWarning)
            writeLine("ANSI codes are not officially implemented in the Windows console, thus the colors and other codes may not work as expected.", LogType.WARNING);

        SOUT.printf(ansiStart + "[%s] %s %s", type.toString(), text, Foreground.RESET);
    }

    public static void clear() throws IOException, InterruptedException
    {
        if (System.isWindows())
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            SOUT.print(Foreground.CLEAR);
    }

    public static void setTitle(String text) throws IOException, InterruptedException
    {
        if (!System.isWindows())
            SOUT.println("\033]2;" + text + "\007");
        else
            new ProcessBuilder("cmd", "/c", "title", text).inheritIO().start().waitFor();
    }

    public static String readLine() throws IOException
    {
        return new BufferedReader(new InputStreamReader(SIN)).readLine();
    }
}
