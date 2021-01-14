package open.java.toolkit.console;

import open.java.toolkit.System;
import open.java.toolkit.console.ansi.Foreground;

import java.io.*;

public class Console
{
    public static boolean showAnsiWarning = true;

    public static void writeLine(String text, LogType type)
    {
        write(text + System.newLine, type);
    }

    public static void write(String text, LogType type)
    {
        java.lang.System.out.printf("[%s] %s", type.toString(), text);
    }

    public static void ansiWriteLine(String ansiStart, String text, LogType type)
    {
        ansiWrite(ansiStart,text + System.newLine, type);
    }

    public static void ansiWrite(String ansiStart, String text, LogType type)
    {
        if (System.isWindows() && showAnsiWarning)
            writeLine("ANSI codes are not officially implemented in the Windows console, thus the colors and other codes may not work as expected.", LogType.WARNING);

        java.lang.System.out.printf(ansiStart + "[%s] %s %s", type.toString(), text, Foreground.RESET);
    }

    public static void clear() throws IOException, InterruptedException
    {
        if (System.isWindows())
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
        {
            java.lang.System.out.print(Foreground.CLEAR);
            java.lang.System.out.flush();
        }
    }

    public static void setTitle(String text) throws IOException, InterruptedException
    {
        if (!System.isWindows())
            java.lang.System.out.print("\033]2;" + text + "\007");
        else
            new ProcessBuilder("cmd", "/c", "title", text).inheritIO().start().waitFor();
    }

    public static String readLine() throws IOException
    {
        return new BufferedReader(new InputStreamReader(java.lang.System.in)).readLine();
    }
}
