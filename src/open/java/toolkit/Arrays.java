package open.java.toolkit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Arrays
{
    public static ArrayList<String> removeDupes(List<String> input)
    {
        Set<String> noDupes = new HashSet<>();
        noDupes.addAll(input);
        return new ArrayList<>(noDupes);
    }

    public static ArrayList<String> toArrayList(String[] input)
    {
        return new ArrayList<>(toList(input));
    }

    public static List<String> toList(String[] input)
    {
        return java.util.Arrays.asList(input);
    }
}
