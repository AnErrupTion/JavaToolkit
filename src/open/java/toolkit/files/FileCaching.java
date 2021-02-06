package open.java.toolkit.files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileCaching
{
    private static final HashMap<String, byte[]> cachedFiles = new HashMap<>();

    public static void cache(String file) throws IOException
    {
        String name = new File(file).getName();
        byte[] content = Files.readBytes(file);

        if (cachedFiles.containsKey(file))
        {
            uncache(file);
            cache(file);
        }
        else cachedFiles.put(name, content);
    }

    public static void uncache(String file)
    {
        cachedFiles.remove(new File(file).getName());
    }

    public static byte[] get(String file)
    {
        return cachedFiles.get(new File(file).getName());
    }
}
