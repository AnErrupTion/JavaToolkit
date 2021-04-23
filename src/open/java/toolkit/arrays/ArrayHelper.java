package open.java.toolkit.arrays;

import open.java.toolkit.System;

import java.util.*;

public class ArrayHelper
{
    /**
     * Removes duplicate elements from a list.
     * @param input The input list.
     * @return The input list without any duplicate elements.
     */
    public static ArrayList<String> removeDuplicateElements(List<String> input)
    {
        return new ArrayList<>(new LinkedHashSet<>(input));
    }

    /**
     * Converts an array to an ArrayList.
     * @param input The input array.
     * @return An ArrayList based off the input array.
     */
    public static ArrayList<String> toArrayList(String[] input)
    {
        return new ArrayList<>(Arrays.asList(input));
    }

    /**
     * Converts an array to a string array.
     * @param input The input array.
     * @return A string array based off the input array.
     */
    public static String[] toStringArray(Object[] input)
    {
        return Arrays.stream(input).toArray(String[]::new);
    }

    /**
     * Converts a list to a string array.
     * @param input The input list.
     * @return A string array based off the input list.
     */
    public static String[] toStringArray(List<String> input)
    {
        return input.toArray(String[]::new);
    }

    /**
     * Checks if an array is empty or not.
     * @param input The input array.
     * @return Whether or not the input array is empty.
     */
    public static boolean isEmpty(Object[] input)
    {
        return input == null || input.length == 0;
    }

    /**
     * Checks if a list is empty or not.
     * @param input The input list.
     * @return Whether or not the input array is empty.
     */
    public static boolean isEmpty(List<?> input)
    {
        return input == null || input.size() == 0;
    }

    /**
     * Converts a list to a string.
     * @param input The input list.
     * @return The input list as a string, showing every line with new line breaks.
     */
    public static String toString(List<String> input)
    {
        return toString(toStringArray(input));
    }

    /**
     * Converts a string array to a string.
     * @param input The input array.
     * @return The input array as a string, showing every line with new line breaks.
     */
    public static String toString(String[] input)
    {
        return Arrays.toString(input).replace("[", "").replace("]", "").replace(", ", System.newLine);
    }

    /**
     * Finds the index of an element in an array.
     * @param input The input array.
     * @param element The element to look for in the array.
     * @return The index of the element in the array.
     */
    public static int indexOf(Object[] input, Object element)
    {
        for (int i = 0; i < input.length; i++)
            if (input[i] == element)
                return i;

        return -1;
    }
}
