package open.java.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Arrays
{
    public static ArrayList<String> removeDupes(List<String> input)
    {
        return new ArrayList<>(new HashSet<>(input));
    }

    public static ArrayList<String> toArrayList(String[] input)
    {
        return new ArrayList<>(java.util.Arrays.asList(input));
    }

    public static String[] toStringArray(Object[] input)
    {
        return java.util.Arrays.stream(input).toArray(String[]::new);
    }

    public static boolean isEmpty(Object[] array)
    {
        return array == null || array.length <= 0;
    }

    public static String toString(List<String> input)
    {
        return java.util.Arrays.toString(input.toArray(String[]::new)).replace("[", "").replace("]", "").replace(", ", System.newLine);
    }
}
