package open.java.toolkit.files;

import open.java.toolkit.Arrays;
import open.java.toolkit.Errors;
import open.java.toolkit.System;

import java.io.*;

public class Files
{
    public static String readFile(String path)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null)
                content.append(line).append("\n");

            return content.toString();
        } catch (IOException ex) { Errors.newError(ex.getMessage()); }

        return null;
    }

    public static String[] readLines(String path)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            return Arrays.toStringArray(br.lines().toArray());
        } catch (IOException ex) { Errors.newError(ex.getMessage()); }

        return null;
    }

    public static byte[] readBytes(String path)
    {
        File file = new File(path);
        FileInputStream input = null;

        try
        {
            input = new FileInputStream(file);
        } catch (FileNotFoundException ex) { Errors.newError(ex.getMessage()); }

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] b = new byte[(int) file.length()];
        int c;

        try
        {
            while ((c = input.read(b)) != -1)
                output.write(b, 0, c);
        } catch (IOException ex) { Errors.newError(ex.getMessage()); }

        return output.toByteArray();
    }

    public static void writeString(String path, String content, boolean append)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append)))
        {
            bw.write(content);
        } catch (IOException ex) { Errors.newError(ex.getMessage()); }
    }

    public static void writeLines(String path, String[] content, boolean append)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append)))
        {
            for (String str : content)
            {
                bw.write(str);
                bw.write(System.newLine);
            }
        } catch (IOException ex) { Errors.newError(ex.getMessage()); }
    }

    public static boolean fileExists(String path)
    {
        File f = new File(path);
        return !f.isDirectory() && f.exists();
    }

    public static boolean createFile(String path)
    {
        try
        {
            return new File(path).createNewFile();
        } catch (IOException ex) { Errors.newError(ex.getMessage()); }

        return false;
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

    public static boolean delete(String path)
    {
        return new File(path).delete();
    }
}
