package com.reddy.university.api.integration;

import com.reddy.university.api.UniversityApplication;
import com.reddy.university.api.UniversityConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;

/**
 * Created by deven on 9/26/2016.
 */
public class RequestSimulationTests {
    @ClassRule
    public static final DropwizardAppRule<UniversityConfiguration> RULE =
            new DropwizardAppRule<UniversityConfiguration>(UniversityApplication.class, resourceFilePath("test-config.yml"));

    @Test
    public void loginHandlerRedirectsAfterPost() throws Exception {
        for(int i = 0; i <= 100; i++){
            Client client = ClientBuilder.newClient();
            final AsyncInvoker asyncInvoker = client.target("http://localhost:8080/report")
                    .request().async();

            final Future<Response> responseFuture = asyncInvoker.get();
            client.close();
        }
    }
}