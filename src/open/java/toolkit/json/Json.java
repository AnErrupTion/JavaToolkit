package open.java.toolkit.json;

public class Json
{
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

                return value;
            }
        }

        return null;
    }
}
