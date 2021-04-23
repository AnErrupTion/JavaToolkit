package open.java.toolkit.files;

import open.java.toolkit.Errors;
import open.java.toolkit.System;

import java.io.*;

public class Files
{
    private static File file;

    /**
     * Reads a file's content, as a string.
     * @param path The path of the file to read from.
     * @return The file's content, as a string.
     */
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

    /**
     * Reads a file's content, as a string array.
     * @param path The path of the file to read from.
     * @return The file's content, as a string array.
     */
    public static String[] readLines(String path)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            return br.lines().toArray(String[]::new);
        } catch (IOException ex) { Errors.newError(ex); }

        return null;
    }

    /**
     * Reads a file's content, as a byte array.
     * @param path The path of the file to read from.
     * @return The file's content, as a byte array.
     */
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

    /**
     * Writes a string to a file.
     * @param path The path of the file to write to.
     * @param content The new content.
     * @param append Whether or not to append the content to the file.
     */
    public static void writeString(String path, String content, boolean append)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append)))
        {
            bw.write(content);
        } catch (IOException ex) { Errors.newError(ex); }
    }

    /**
     * Writes lines to a file.
     * @param path The path of the file to write to.
     * @param content The new content.
     * @param append Whether or not to append the content to the file.
     */
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

    /**
     * Writes bytes to a file.
     * @param path The path of the file to write to.
     * @param content The new content.
     * @param append Whether or not to append the content to the file.
     */
    public static void writeBytes(String path, byte[] content, boolean append)
    {
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(path, append)))
        {
            os.write(content);
        }
        catch (IOException ex) { Errors.newError(ex); }
    }

    /**
     * Checks if a file exists or not.
     * @param path The path of the file to check from.
     * @return Whether or not the file exists.
     */
    public static boolean fileExists(String path)
    {
        file = new File(path);
        return !file.isDirectory() && file.exists();
    }

    /**
     * Creates a file if it doesn't already exists.
     * @param path The path of the file to create.
     * @return Whether or not the file has been successfully created.
     */
    public static boolean createFile(String path)
    {
        try
        {
            file = new File(path);
            return file.createNewFile();
        } catch (IOException ex) { Errors.newError(ex); }

        return false;
    }

    /**
     * Creates a directory if it doesn't already exists.
     * @param path The path of the directory to create.
     * @return Whether or not the directory has been successfully created.
     */
    public static boolean createDirectory(String path)
    {
        file = new File(path);
        return file.mkdir();
    }

    /**
     * Checks if a directory exists or not.
     * @param path The path of the directory to check from.
     * @return Whether or not the directory exists.
     */
    public static boolean directoryExists(String path)
    {
        file = new File(path);
        return file.isDirectory() && file.exists();
    }

    /**
     * Deletes a file or a directory.
     * @param path The path of the file/directory to delete.
     * @return Whether or not the file/directory has been successfully deleted.
     */
    public static boolean delete(String path)
    {
        file = new File(path);
        return file.delete();
    }
}
