package open.java.toolkit.http;

public class RequestUtils
{
    /**
     * Retrieves the domain from a URL.
     * @param url The URL to retrieve the domain from.
     * @return The domain.
     */
    public static String urlDomainOnly(String url)
    {
        return urlNoSubpages(urlNoWww(urlNoProtocol(url)));
    }

    /**
     * Retrieves the URL without any subpages.
     * @param url The URL to parse from.
     * @return The URL without any subpages.
     */
    public static String urlNoSubpages(String url)
    {
        return url.split("/")[0];
    }

    /**
     * Retrieves the URL without the "www" string.
     * @param url The URL to parse from.
     * @return The URL without the "www" string.
     */
    public static String urlNoWww(String url)
    {
        return url.replace("www.", "");
    }

    /**
     * Retrieves the URL without the protocol.
     * @param url The URL to parse from.
     * @return The URL without the protocol.
     */
    public static String urlNoProtocol(String url)
    {
        return url.contains("http://") ? url.replace("http://", "") : url.replace("https://", "");
    }
}
