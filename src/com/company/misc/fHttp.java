package com.company.misc;

import com.company.Login;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//fHttp - my http functions
public class fHttp {
    public static String responseBody(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }
    public static int responseStatusCode(HttpResponse response){
        return response.getStatusLine().getStatusCode();
    }
    //simple GET/POST. No response
    public static void sendNoResp(HttpClient client, HttpUriRequest request) throws IOException {
        EntityUtils.consume(client.execute(request).getEntity());
    }
    public static void consume(HttpResponse response) throws IOException {
        EntityUtils.consume(response.getEntity());
    }
    public static Document getDocument(Login login, String url) throws IOException {
        HttpGet get = new HttpGet(url);
        HttpResponse response = login.client.execute(get);

        return Jsoup.parse(fHttp.responseBody(response));
    }
}
