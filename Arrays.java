package open.java.toolkit;

import java.util.ArrayList;
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
}
