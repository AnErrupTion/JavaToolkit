package open.java.toolkit.files;

import java.io.IOException;

public class FileParser
{
    public String[] content;
    private String filePath, commentCharacter, separator;
    private boolean removeSpaces;
    private int index;

    public FileParser(String filePath, String commentCharacter, String separator, boolean removeSpaces, int index) throws IOException
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

    public void setString(String key, String value) throws IOException
    {
        for (int i = 0; i < content.length; i++)
        {
            String line = content[i];
            if (!line.startsWith(commentCharacter) && line.contains(key))
            {
                String result = line.split(separator)[index];
                String copy = result;

                if (removeSpaces)
                    result = result.replace(" ", "");

                result = value;
                content[i] = line.replace(copy, result);

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
            return 0;
        }
    }

    public void setInt(String key, int value) throws IOException
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
            return false;
        }
    }

    public void setBoolean(String key, boolean value) throws IOException
    {
        setString(key, String.valueOf(value));
    }

    public String[] getFile(String key)
    {
        try
        {
            String[] array = Files.readLines(key);
            return array != null && array.length > 0 ? array : null;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
