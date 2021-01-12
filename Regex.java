package open.java.toolkit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex
{
    public static ArrayList<String> getMatches(String input, String regex)
    {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        ArrayList<String> matches = new ArrayList<>();

        while (matcher.find())
            matches.add(matcher.group());

        return matches;
    }
}
