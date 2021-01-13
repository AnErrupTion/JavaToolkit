package open.java.toolkit.files;

import open.java.toolkit.Arrays;
import open.java.toolkit.System;

import java.io.*;

public class Files
{
    public static String readFile(String path) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null)
                content.append(line + "\n");

            return content.toString();
        }
    }

    public static String[] readLines(String path) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            return Arrays.toStringArray(br.lines().toArray());
        }
    }

    public static void writeString(String path, String content, boolean append) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append)))
        {
            bw.write(content);
        }
    }

    public static void writeLines(String path, String[] content, boolean append) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append)))
        {
            for (String str : content)
            {
                bw.write(str);
                bw.write(System.newLine);
            }
        }
    }

    public static boolean fileExists(String path)
    {
        File f = new File(path);
        return !f.isDirectory() && f.exists();
    }

    public static boolean createFile(String path) throws IOException
    {
        return new File(path).createNewFile();
    }

    public static boolean createDirectory(String path)
    {
        return new File(path).mkdir();
    }

    public static boolean directoryExists(String path)
    {
        File f = new File(path);
        return f.isDirectory() && f.exists();
    }
}
