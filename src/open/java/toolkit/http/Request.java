package open.java.toolkit.http;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Request
{
    private static final HttpClient.Builder client = HttpClient.newBuilder();
    private static String contentType = "application/x-www-form-urlencoded";

    public static void setVersion(HttpClient.Version version)
    {
        client.version(version);
    }

    public static void setTimeout(int timeout)
    {
        client.connectTimeout(Duration.ofMillis(timeout));
    }

    public static void setFollowRedirects(HttpClient.Redirect redirect)
    {
        client.followRedirects(redirect);
    }

    public static void setProxy(ProxySelector proxy)
    {
        client.proxy(proxy);
    }

    public static void setAuthenticator(Authenticator auth)
    {
        client.authenticator(auth);
    }

    public static void setCookieHandler(CookieHandler handler)
    {
        client.cookieHandler(handler);
    }

    public static void setExecutor(Executor executor)
    {
        client.executor(executor);
    }

    public static void setPriority(int priority)
    {
        client.priority(priority);
    }

    public static void setSslContext(SSLContext context)
    {
        client.sslContext(context);
    }

    public static void setSslParameters(SSLParameters parameters)
    {
        client.sslParameters(parameters);
    }

    public static void setContentType(String contentType)
    {
        Request.contentType = contentType;
    }

    public static HttpClient.Builder getClientBuilder()
    {
        return client;
    }

    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data)
    {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Object, Object> entry : data.entrySet())
        {
            if (builder.length() > 0)
                builder.append("&");

            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }

        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public static HttpResponse<String> sendGet(String url) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Mozilla/5.0")
                .header("Content-Type", contentType)
                .build();

        return client.build().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> sendPost(String url, Map<Object, Object> formData) throws IOException, InterruptedException
    {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Mozilla/5.0")
                .header("Content-Type", contentType)
                .build();

        return client.build().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static CompletableFuture<HttpResponse<String>> sendGetAsync(String url)
    {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Mozilla/5.0")
                .build();

        return client.build().sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public static CompletableFuture<HttpResponse<String>> sendPostAsync(String url, Map<Object, Object> formData)
    {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Mozilla/5.0")
                .header("Content-Type", contentType)
                .build();

        return client.build().sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
