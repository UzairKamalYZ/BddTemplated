package be.techfix.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class AcceptanceTestEnvironment {

    private static final String WIREMOCK_PORT = System.getProperty("wiremock.port", "9095");
    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(Integer.parseInt(WIREMOCK_PORT)));

    @Before
    public static void setupWireMock() {
        System.out.println("----- Starting Wiremock Server -----");
        if (!wireMockServer.isRunning()) {
            wireMockServer.start();
            WireMock.configureFor("localhost", Integer.parseInt(WIREMOCK_PORT));
        }
    }

    @After
    public static void tearDownWireMock() {
        System.out.println("----- Stopping Wiremock Server -----");
        wireMockServer.stop();
    }
}