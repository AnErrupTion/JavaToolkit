package open.java.toolkit.http;

import open.java.toolkit.Errors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Request
{
    private static HttpClient.Builder client = HttpClient.newBuilder();
    private static HttpClient built = null;
    private static String contentType = "text/html; charset=UTF-8";
    private static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
    private static final ArrayList<String> headers = new ArrayList<>();
    
    public static void setVersion(HttpClient.Version version)
    {
        client = client.version(version);
    }

    public static void setTimeout(int timeout)
    {
        client = client.connectTimeout(Duration.ofMillis(timeout));
    }

    public static void setFollowRedirects(HttpClient.Redirect redirect)
    {
        client = client.followRedirects(redirect);
    }

    public static void setProxy(ProxySelector proxy)
    {
        client = client.proxy(proxy);
    }

    public static void setAuthenticator(Authenticator auth)
    {
        client = client.authenticator(auth);
    }

    public static void setCookieHandler(CookieHandler handler)
    {
        client = client.cookieHandler(handler);
    }

    public static void setExecutor(Executor executor)
    {
        client = client.executor(executor);
    }

    public static void setPriority(int priority)
    {
        client = client.priority(priority);
    }

    public static void setSslContext(SSLContext context)
    {
        client = client.sslContext(context);
    }

    public static void setSslParameters(SSLParameters parameters)
    {
        client = client.sslParameters(parameters);
    }

    public static void setContentType(String contentType)
    {
        Request.contentType = contentType;
    }

    public static void setUserAgent(String userAgent)
    {
        Request.userAgent = userAgent;
    }

    public static void addHeader(String name, String value)
    {
        headers.add(name + ":" + value);
    }

    public static void setProxy(String proxy)
    {
        String[] array = proxy.split(":");
        setProxy(ProxySelector.of(new InetSocketAddress(array[0], Integer.parseInt(array[1]))));
    }

    public static void forceBuild()
    {
        built = client.build();
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

    public static HttpResponse<String> sendGet(String url)
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);
        
        for (String header : headers)
        {
            String[] array = header.split(":");
            builder = builder.setHeader(array[0], array[1]);
        }

        HttpRequest request = builder.build();
        if (built == null)
            built = client.build();
        
        try
        {
            return built.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) { Errors.newError(ex); }

        return new HttpResponse<>()
        {
            @Override
            public int statusCode()
            {
                return 0;
            }

            @Override
            public HttpRequest request()
            {
                return request;
            }

            @Override
            public Optional<HttpResponse<String>> previousResponse()
            {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers()
            {
                return null;
            }

            @Override
            public String body()
            {
                return "";
            }

            @Override
            public Optional<SSLSession> sslSession()
            {
                return Optional.empty();
            }

            @Override
            public URI uri()
            {
                return URI.create("https://www.google.com");
            }

            @Override
            public HttpClient.Version version()
            {
                return HttpClient.Version.HTTP_1_1;
            }
        };
    }

    public static HttpResponse<String> sendPost(String url, Map<Object, Object> formData)
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);
        
        for (String header : headers)
        {
            String[] array = header.split(":");
            builder = builder.setHeader(array[0], array[1]);
        }

        HttpRequest request = builder.build();
        if (built == null)
            built = client.build();
        
        try
        {
            return built.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) { Errors.newError(ex); }

        return new HttpResponse<>()
        {
            @Override
            public int statusCode()
            {
                return 0;
            }

            @Override
            public HttpRequest request()
            {
                return request;
            }

            @Override
            public Optional<HttpResponse<String>> previousResponse()
            {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers()
            {
                return null;
            }

            @Override
            public String body()
            {
                return "";
            }

            @Override
            public Optional<SSLSession> sslSession()
            {
                return Optional.empty();
            }

            @Override
            public URI uri()
            {
                return URI.create("https://www.google.com");
            }

            @Override
            public HttpClient.Version version()
            {
                return HttpClient.Version.HTTP_1_1;
            }
        };
    }

    public static HttpResponse<String> sendPost(String url, String formData)
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);
        
        for (String header : headers)
        {
            String[] array = header.split(":");
            builder = builder.setHeader(array[0], array[1]);
        }

        HttpRequest request = builder.build();
        if (built == null)
            built = client.build();
        
        try
        {
            return built.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) { Errors.newError(ex); }

        return new HttpResponse<>()
        {
            @Override
            public int statusCode()
            {
                return 0;
            }

            @Override
            public HttpRequest request()
            {
                return request;
            }

            @Override
            public Optional<HttpResponse<String>> previousResponse()
            {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers()
            {
                return null;
            }

            @Override
            public String body()
            {
                return "";
            }

            @Override
            public Optional<SSLSession> sslSession()
            {
                return Optional.empty();
            }

            @Override
            public URI uri()
            {
                return URI.create("https://www.google.com");
            }

            @Override
            public HttpClient.Version version()
            {
                return HttpClient.Version.HTTP_1_1;
            }
        };
    }

    public static CompletableFuture<HttpResponse<String>> sendGetAsync(String url)
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);
        
        for (String header : headers)
        {
            String[] array = header.split(":");
            builder = builder.setHeader(array[0], array[1]);
        }

        HttpRequest request = builder.build();
        if (built == null)
            built = client.build();
        
        return built.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public static CompletableFuture<HttpResponse<String>> sendPostAsync(String url, Map<Object, Object> formData)
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);
        
        for (String header : headers)
        {
            String[] array = header.split(":");
            builder = builder.setHeader(array[0], array[1]);
        }

        HttpRequest request = builder.build();
        if (built == null)
            built = client.build();
        
        return built.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public static CompletableFuture<HttpResponse<String>> sendPostAsync(String url, String formData)
    {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .uri(URI.create(url))
                .setHeader("User-Agent", userAgent)
                .setHeader("Content-Type", contentType);
        
        for (String header : headers)
        {
            String[] array = header.split(":");
            builder = builder.setHeader(array[0], array[1]);
        }

        HttpRequest request = builder.build();
        if (built == null)
            built = client.build();
        
        return built.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
