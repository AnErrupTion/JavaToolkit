package open.java.toolkit.files;

import open.java.toolkit.arrays.ArrayHelper;
import open.java.toolkit.Errors;

public class FileParser
{
    private final String[] content;
    private final String filePath, commentCharacter, separator;
    private final boolean removeSpaces;
    private final int index;

    public FileParser(String filePath, String commentCharacter, String separator, boolean removeSpaces, int index)
    {
        this.filePath = filePath;
        this.commentCharacter = commentCharacter;
        this.separator = separator;
        this.removeSpaces = removeSpaces;
        this.index = index;

        content = Files.readLines(this.filePath);
    }

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

    public void setInt(String key, int value)
    {
        setString(key, String.valueOf(value));
    }

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

    public void setBoolean(String key, boolean value)
    {
        setString(key, String.valueOf(value));
    }

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
