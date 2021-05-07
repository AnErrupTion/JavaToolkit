package open.java.toolkit.json;

public class Json
{
    /**
     * Parses a value with a specific key from a JSON string.
     * @param key The key to get the value with.
     * @param str The JSON string to perform the parsing with.
     * @return The parsed value.
     */
    public static String parseValue(String key, String str)
    {
        String[] array = str.split(":");
        for (int i = 0; i < array.length; i++)
        {
            String s = array[i];

            if (s.contains(key))
            {
                String value = array[i + 1];

                if (value.contains(","))
                    value = value.split(",")[0];
                else
                    value = value.substring(0, value.length() - 1);

                if (value.startsWith("\""))
                    value = value.substring(1);

                if (value.endsWith("\""))
                    value = value.substring(0, value.length() - 1);

                if (value.startsWith(" \""))
                    value = value.substring(2);

                if (value.startsWith(" [\""))
                    value = value.substring(3);

                return value;
            }
        }

        return null;
    }

    /**
     * Beautifies a JSON string.
     * @param str The JSON string to beautify.
     * @return The beautified JSON string.
     */
    public static String beautify(String str)
    {
        int tabCount = 0;

        StringBuilder builder = new StringBuilder();
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++)
        {
            String ch = String.valueOf(chars[i]);

            if (ch.equals("}") || ch.equals("]"))
            {
                tabCount--;
                String ch1 = String.valueOf(chars[i - 1]);

                if (!ch1.equals("[") && !ch1.equals("{"))
                    builder.append(newLine(tabCount));
            }
            builder.append(ch);

            if (ch.equals("{") || ch.equals("["))
            {
                tabCount++;
                String ch1 = String.valueOf(chars[i + 1]);

                if (ch1.equals("]") || ch1.equals("}"))
                    continue;

                builder.append(newLine(tabCount));
            }

            if (ch.equals(","))
            {
                String ch1 = String.valueOf(chars[i - 1]);
                if (ch1.equals("\"") || ch1.equals("]") || ch1.equals("}"))
                    builder.append(newLine(tabCount));
            }
        }

        return builder.toString();
    }

    private static String newLine(int tabCount)
    {
        return "\n" + "  ".repeat(tabCount);
    }
}
