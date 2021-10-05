package be.techfix.bdd.rest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final CloseableHttpClient httpClient = HttpClients.createMinimal();
    @GetMapping("/hello")
    public String helloWorld() throws IOException {
        return "Hello there with some " + getExternalData();
    }

    private String getExternalData() throws IOException {
        HttpGet httpRequest = new HttpGet("http://localhost:9095/anotherService/BringMeExternalData");
        HttpResponse response = httpClient.execute(httpRequest);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    @GetMapping("/bye")
    public String goodBye() {
        return "Good Bye !!!";
    }
}
