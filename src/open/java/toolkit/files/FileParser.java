package open.java.toolkit.files;

import open.java.toolkit.arrays.ArrayHelper;
import open.java.toolkit.Errors;

public class FileParser
{
    private final String[] content;
    private final String filePath, commentCharacter, separator;
    private final boolean removeSpaces;
    private final int index;

    /**
     * Constructor for this class.
     * @param filePath The file to read.
     * @param commentCharacter The character which is used as a comment or "line to ignore" character.
     * @param separator The separator character used to separate the keys and values.
     * @param removeSpaces Whether or not to remove spaces when reading lines.
     * @param index The index which is used with the separator. Generally, 0 is the key and 1 is the value.
     */
    public FileParser(String filePath, String commentCharacter, String separator, boolean removeSpaces, int index)
    {
        this.filePath = filePath;
        this.commentCharacter = commentCharacter;
        this.separator = separator;
        this.removeSpaces = removeSpaces;
        this.index = index;

        content = Files.readLines(this.filePath);
    }

    /**
     * Gets a string from a specific key.
     * @param key The key used to get the string.
     * @return The string.
     */
    public String getString(String key)
    {
        for (String line : content)
            if (!line.startsWith(commentCharacter) && line.contains(key))
            {
                String result = line.split(separator)[index];
                return removeSpaces ? result.replace(" ", "") : result;
            }

        return null;
    }

    /**
     * Sets a string to a new one.
     * @param key The key used to get the string in the file.
     * @param value The new string used to replace the old one.
     */
    public void setString(String key, String value)
    {
        for (int i = 0; i < content.length; i++)
        {
            String line = content[i];
            if (!line.startsWith(commentCharacter) && line.contains(key))
            {
                String result = line.split(separator)[index];
                content[i] = line.replace(result, " " + value);
                break;
            }
        }

        Files.writeLines(filePath, content, false);
    }

    /**
     * Gets an integer from a specific key.
     * @param key The key used to get the integer.
     * @return The integer.
     */
    public int getInt(String key)
    {
        try
        {
            return Integer.parseInt(getString(key));
        }
        catch (Exception ex)
        {
            Errors.newError(ex);
            return 0;
        }
    }

    /**
     * Sets an integer to a new one.
     * @param key The key used to get the integer in the file.
     * @param value The new integer used to replace the old one.
     */
    public void setInt(String key, int value)
    {
        setString(key, String.valueOf(value));
    }

    /**
     * Gets a boolean from a specific key.
     * @param key The key used to get the boolean.
     * @return The boolean.
     */
    public boolean getBoolean(String key)
    {
        try
        {
            return Boolean.parseBoolean(getString(key));
        }
        catch (Exception ex)
        {
            Errors.newError(ex);
            return false;
        }
    }

    /**
     * Sets a boolean to a new one.
     * @param key The key used to get the boolean in the file.
     * @param value The new boolean used to replace the old one.
     */
    public void setBoolean(String key, boolean value)
    {
        setString(key, String.valueOf(value));
    }

    /**
     * Gets a file's content from a specific key.
     * @param key The key used to get the file's content.
     * @return The file's content as a string array.
     */
    public String[] getFile(String key)
    {
        try
        {
            String[] array = Files.readLines(key);
            return !ArrayHelper.isEmpty(array) ? array : null;
        }
        catch (Exception ex)
        {
            Errors.newError(ex);
            return null;
        }
    }
}
