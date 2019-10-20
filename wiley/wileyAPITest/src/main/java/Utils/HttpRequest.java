package Utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class HttpRequest {

    /**
     * GET запрос на заданный uri
     * @param uri - адрес запроса
     *
     * @return - InputStream, содержащий тело ответа
     */
    public InputStream httpGetRequestResult(String uri) {
        InputStream result;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpUriRequest request = new HttpGet(uri);
            HttpResponse response = client.execute(request);
            response.getEntity().getContent();
            result = response.getEntity().getContent();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * POST запрос на заданный uri
     * @param uri - адрес запроса
     *
     * @return - InputStream, содержащий тело ответа
     */
    public InputStream httpPostRequestResult(String uri) {
        InputStream result;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpUriRequest request = new HttpPost(uri);
            HttpResponse response = client.execute(request);
            response.getEntity().getContent();
            result = response.getEntity().getContent();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
