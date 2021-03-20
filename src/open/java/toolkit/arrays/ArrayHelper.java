package open.java.toolkit.arrays;

import open.java.toolkit.System;

import java.util.*;

public class ArrayHelper
{
    public static ArrayList<Object> removeDuplicateElements(List<Object> input)
    {
        return new ArrayList<>(new LinkedHashSet<>(input));
    }

    public static ArrayList<Object> toArrayList(Object[] input)
    {
        return new ArrayList<>(Arrays.asList(input));
    }

    public static String[] toStringArray(Object[] input)
    {
        return Arrays.stream(input).toArray(String[]::new);
    }

    public static String[] toStringArray(Collection<String> input)
    {
        return input.toArray(String[]::new);
    }

    public static boolean isEmpty(Object[] array)
    {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection array)
    {
        return array == null || array.size() == 0;
    }

    public static String toString(Collection<String> input)
    {
        return toString(toStringArray(input));
    }

    public static String toString(String[] input)
    {
        return java.util.Arrays.toString(input).replace("[", "").replace("]", "").replace(", ", System.newLine);
    }
}
