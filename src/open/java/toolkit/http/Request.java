package open.java.toolkit.http;

import open.java.toolkit.Errors;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class Request
{
    private static HttpClient.Builder builder = HttpClient.newBuilder();
    private static final HashMap<String, String> headers = new HashMap<>();

    private static HttpResponse.BodyHandler<?> handler = HttpResponse.BodyHandlers.ofString();

    private static final String[] userAgents = new String[]
            {
                    "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 OPR/75.0.3969.171",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Edg/90.0.818.42",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Vivaldi/3.7"
            };
    private static String userAgent = userAgents[0], contentType = "text/html; charset=UTF-8";

    /**
     * Gets the current HttpClient builder.
     * @return The current HttpClient builder.
     */
    public static HttpClient.Builder getBuilder()
    {
        return builder;
    }

    /**
     * Sets the User-Agent string to a new one.
     * @param userAgent The new User-Agent string.
     */
    public static void setUserAgent(String userAgent)
    {
        Request.userAgent = userAgent;
    }

    /**
     * Sets the Content-Type string to a new one.
     * @param contentType The new Content-Type string.
     */
    public static void setContentType(String contentType)
    {
        Request.contentType = contentType;
    }

    /**
     * Sets the HttpResponse BodyHandler to a new one.
     * @param handler The new HttpResponse BodyHandler.
     */
    public static void setBodyHandler(HttpResponse.BodyHandler<?> handler)
    {
        Request.handler = handler;
    }

    /**
     * Sets the current request timeout to a new one.
     * @param timeout The new timeout integer, in milliseconds.
     */
    public static void setTimeout(int timeout)
    {
        builder = builder.connectTimeout(Duration.ofMillis(timeout));
    }

    /**
     * Adds a header to the current request.
     * @param name The header name.
     * @param value The header value.
     */
    public static void addHeader(String name, String value)
    {
        headers.put(name, value);
    }

    /**
     * Sets the proxy to be used within the current request.
     * @param proxy The new proxy string, containing both IP and port with a colon separating both.
     */
    public static void setProxy(String proxy)
    {
        String[] array = proxy.split(":");
        builder = builder.proxy(ProxySelector.of(new InetSocketAddress(array[0], Integer.parseInt(array[1]))));
    }

    /**
     * Randomizes the current User-Agent.
     */
    public static void randomizeUserAgent()
    {
        userAgent = userAgents[ThreadLocalRandom.current().nextInt(userAgents.length)];
    }

    /**
     * Sends a HTTP(S) request to a specific URL, with a specific method and BodyPublisher.
     * @param url The URL to perform the request to.
     * @param method The method to perform the request with.
     * @param publisher The BodyPublisher to perform the request with.
     * @return A HttpResponse object.
     */
    public static HttpResponse<?> send(String url, String method, HttpRequest.BodyPublisher publisher)
    {
        HttpRequest.Builder reqBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);

        if (method.equalsIgnoreCase("get"))
            reqBuilder = reqBuilder.GET();
        else if (method.equalsIgnoreCase("post"))
            reqBuilder = reqBuilder.POST(publisher);
        else if (method.equalsIgnoreCase("delete"))
            reqBuilder = reqBuilder.DELETE();
        else if (method.equalsIgnoreCase("put"))
            reqBuilder = reqBuilder.PUT(publisher);

        for (Map.Entry<String, String> entry : headers.entrySet())
            reqBuilder = reqBuilder.setHeader(entry.getKey(), entry.getValue());

        HttpClient client = builder.build();
        HttpRequest request = reqBuilder.build();

        try
        {
            return client.send(request, handler);
        } catch (IOException | InterruptedException ex) { Errors.newError(ex); }

        return null;
    }

    /**
     * Sends an asynchronous HTTP(S) request to a specific URL, with a specific method and BodyPublisher.
     * @param url The URL to perform the request to.
     * @param method The method to perform the request with.
     * @param publisher The BodyPublisher to perform the request with.
     * @return A CompletableFuture object containing a HttpResponse object.
     */
    public static CompletableFuture<?> sendAsync(String url, String method, HttpRequest.BodyPublisher publisher)
    {
        HttpRequest.Builder reqBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);

        if (method.equalsIgnoreCase("get"))
            reqBuilder = reqBuilder.GET();
        else if (method.equalsIgnoreCase("post"))
            reqBuilder = reqBuilder.POST(publisher);
        else if (method.equalsIgnoreCase("delete"))
            reqBuilder = reqBuilder.DELETE();
        else if (method.equalsIgnoreCase("put"))
            reqBuilder = reqBuilder.PUT(publisher);

        for (Map.Entry<String, String> entry : headers.entrySet())
            reqBuilder = reqBuilder.setHeader(entry.getKey(), entry.getValue());

        HttpClient client = builder.build();
        HttpRequest request = reqBuilder.build();

        return client.sendAsync(request, handler);
    }
}
