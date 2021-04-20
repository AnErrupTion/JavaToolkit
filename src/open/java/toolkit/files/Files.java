package open.java.toolkit.files;

import open.java.toolkit.Errors;
import open.java.toolkit.System;

import java.io.*;

public class Files
{
    private static File file;
    
    public static String readFile(String path)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = br.readLine()) != null)
                content.append(line).append("\n");
            
            return content.toString();
        } catch (IOException ex) { Errors.newError(ex); }

        return null;
    }

    public static String[] readLines(String path)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            return br.lines().toArray(String[]::new);
        } catch (IOException ex) { Errors.newError(ex); }

        return null;
    }

    public static byte[] readBytes(String path)
    {
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(path)))
        {
            byte[] bytes = new byte[is.available()];
            int read = is.read(bytes);
            
            if (read > bytes.length)
                Errors.newError(new Exception("Buffer was to small for file, read " + read + " bytes, buffer was " + bytes.length + " bytes."));
            
            return bytes;
        }
        catch (IOException ex) { Errors.newError(ex); }

        return null;
    }

    public static void writeString(String path, String content, boolean append)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append)))
        {
            bw.write(content);
        } catch (IOException ex) { Errors.newError(ex); }
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
        } catch (IOException ex) { Errors.newError(ex); }
    }

    public static void writeBytes(String path, byte[] content, boolean append)
    {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path, append)))
        {
            os.write(content);
        }
        catch (IOException ex) { Errors.newError(ex); }
    }

    public static boolean fileExists(String path)
    {
        file = new File(path);
        return !file.isDirectory() && file.exists();
    }

    public static boolean createFile(String path)
    {
        try
        {
            file = new File(path);
            return file.createNewFile();
        } catch (IOException ex) { Errors.newError(ex); }

        return false;
    }

    public static boolean createDirectory(String path)
    {
        file = new File(path);
        return file.mkdir();
    }

    public static boolean directoryExists(String path)
    {
        file = new File(path);
        return file.isDirectory() && file.exists();
    }

    public static boolean delete(String path)
    {
        file = new File(path);
        return file.delete();
    }
}
