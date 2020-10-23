package open.java.toolkit.files;

import java.io.IOException;

public class FileParser
{
    public String[] file;
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

        file = Files.readFile(this.filePath).split("\n");
    }

    public String parseString(String key)
    {
        for (String line : file)
            if (!line.startsWith(commentCharacter) && line.contains(key))
            {
                String result = line.split(separator)[index];
                return removeSpaces ? result.replace(" ", "") : result;
            }

        return null;
    }

    public int parseInt(String key)
    {
        try
        {
            return Integer.parseInt(parseString(key));
        }
        catch (Exception ex)
        {
            return 0;
        }
    }

    public boolean parseBoolean(String key)
    {
        try
        {
            return Boolean.parseBoolean(parseString(key));
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public String[] parseStringArray(String key) throws IOException
    {
        try
        {
            String[] array = Files.readFile(key).split("\n");
            return array != null && array.length > 0 ? array : null;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
