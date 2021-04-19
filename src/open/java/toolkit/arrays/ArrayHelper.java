package open.java.toolkit.arrays;

import open.java.toolkit.System;

import java.util.*;

public class ArrayHelper
{
    public static ArrayList<String> removeDuplicateElements(List<String> input)
    {
        return new ArrayList<>(new LinkedHashSet<>(input));
    }

    public static ArrayList<String> removeDuplicateElements(String[] input)
    {
        ArrayList<String> list = new ArrayList<>(new LinkedHashSet<>());
        Collections.addAll(list, input);
        return list;
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

    public static boolean isEmpty(Object[] input)
    {
        return input == null || input.length == 0;
    }

    public static boolean isEmpty(List<?> input)
    {
        return input == null || input.size() == 0;
    }

    public static String toString(List<String> input)
    {
        return toString(toStringArray(input));
    }

    public static String toString(String[] input)
    {
        return Arrays.toString(input).replace("[", "").replace("]", "").replace(", ", System.newLine);
    }

    public static int indexOf(Object[] input, Object element)
    {
        for (int i = 0; i < input.length; i++)
            if (input[i] == element)
                return i;

        return -1;
    }
}
