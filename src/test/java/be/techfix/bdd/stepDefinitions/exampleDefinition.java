package be.techfix.bdd.stepDefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.github.tomakehurst.wiremock.client.WireMock;

public class exampleDefinition {
    private final CloseableHttpClient httpClient = HttpClients.createMinimal();
    private HttpResponse httpResponse;

    @Then("user should receive message {string}")
    public void userShouldReceiveMessageHello(String message) throws IOException {
        assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        HttpEntity entity = httpResponse.getEntity();
        String value = EntityUtils.toString(entity);
        assertThat(value).isEqualTo(message);
    }

    @Given("A user uzair")
    public void aUserUzair() {
    }

    @When("user call hello")
    public void userCallHello() throws IOException {
        mockExternalDataCall();
        HttpGet httpRequest = new HttpGet("http://localhost:8080/bdd/hello");
        httpResponse = httpClient.execute(httpRequest);
    }

    @When("user call bye")
    public void userCallBye() throws IOException {
        HttpGet httpRequest = new HttpGet("http://localhost:8080/bdd/bye");
        httpResponse = httpClient.execute(httpRequest);
    }
    public static void mockExternalDataCall() {
        WireMock.stubFor(
                WireMock.get("/anotherService/BringMeExternalData")
                        .willReturn(WireMock.status(200).withBody("External Data")));
    }
}
