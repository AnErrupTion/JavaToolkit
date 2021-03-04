package open.java.toolkit.console;

import open.java.toolkit.Errors;
import open.java.toolkit.System;
import open.java.toolkit.console.ansi.Foreground;
import open.java.toolkit.diagnostics.Process;
import java.io.*;

public class Console
{
    private static final ProcessBuilder cls = Process.createProcess("cmd /c cls", true);
    private static final ProcessBuilder title = Process.createProcess("cmd /c title", true);
    
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
        java.lang.System.out.printf(ansiStart + "[%s] %s %s", type.toString(), text, Foreground.RESET);
    }

    public static void clear()
    {
        if (System.isWindows())
        {
            try
            {
                cls.start().waitFor();
            } catch (IOException | InterruptedException ex) { Errors.newError(ex); }
        }
        else
        {
            java.lang.System.out.print(Foreground.CLEAR);
            java.lang.System.out.flush();
        }
    }

    public static void setTitle(String text)
    {
        if (System.isWindows())
        {
            try
            {
                title.command("cmd", "/c", "title", text);
                title.start().waitFor();
            } catch (IOException | InterruptedException ex) { Errors.newError(ex); }
        } else java.lang.System.out.print("\033]2;" + text + "\007");
    }

    public static String readLine()
    {
        try
        {
            return new BufferedReader(new InputStreamReader(java.lang.System.in)).readLine();
        } catch (IOException ex) { Errors.newError(ex); }

        return null;
    }
}
