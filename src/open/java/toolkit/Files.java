package open.java.toolkit;

import java.io.*;

public class Files
{
    public static String readFile(String path) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path))))
        {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null)
                content.append(line + "\n");

            return content.toString();
        }
    }

    public static void writeFile(String path, String content, boolean append) throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), append)))
        {
            bw.write(content);
        }
    }

    public static boolean fileExists(String path)
    {
        File f = new File(path);
        return f.exists() && !f.isDirectory();
    }
}
