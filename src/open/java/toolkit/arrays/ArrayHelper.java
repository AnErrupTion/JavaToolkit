package open.java.toolkit.arrays;

import open.java.toolkit.System;
import java.util.*;

public class ArrayHelper
{
    public static ArrayList<String> removeDuplicateElements(List<String> input)
    {
        return new ArrayList<>(new LinkedHashSet<>(input));
    }

    public static ArrayList<String> toArrayList(String[] input)
    {
        return new ArrayList<>(Arrays.asList(input));
    }

    public static String[] toStringArray(Object[] input)
    {
        return Arrays.stream(input).toArray(String[]::new);
    }

    public static String[] toStringArray(List<String> input)
    {
        return input.toArray(String[]::new);
    }

    public static boolean isEmpty(Object[] array)
    {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(List<Object> array)
    {
        return array == null || array.size() == 0;
    }

    public static String toString(List<String> input)
    {
        return toString(toStringArray(input));
    }

    public static String toString(String[] input)
    {
        return java.util.Arrays.toString(input).replace("[", "").replace("]", "").replace(", ", System.newLine);
    }
}
