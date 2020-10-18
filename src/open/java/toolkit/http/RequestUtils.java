package open.java.toolkit.http;

public class RequestUtils
{
    public static String urlDomainOnly(String url)
    {
        return urlNoSubpages(urlNoWww(urlNoProtocol(url)));
    }

    public static String urlNoSubpages(String url)
    {
        return url.split("/")[0];
    }

    public static String urlNoWww(String url)
    {
        return url.replace("www.", "");
    }

    public static String urlNoProtocol(String url)
    {
        return url.contains("http://") ? url.replace("http://", "") : url.replace("https://", "");
    }
}
