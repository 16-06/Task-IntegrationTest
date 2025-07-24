package org.example.taskspring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8089)
@AutoConfigureWebTestClient
public class GithubIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("github.api.base-url", () -> "http://localhost:8089");
    }

    @BeforeEach
    void setUpWiremockStubs() {
        stubFor(get(urlEqualTo("/users/octocat/repos"))
                .willReturn(okJson("""
            [
              {
                "name": "not-fork-repo",
                "fork": false,
                "owner": { "login": "octocat" }
              },
              {
                "name": "forked-repo",
                "fork": true,
                "owner": { "login": "octocat" }
              }
            ]
            """)));

        stubFor(get(urlEqualTo("/repos/octocat/not-fork-repo/branches"))
                .willReturn(okJson("""
            [
              {
                "name": "main",
                "commit": { "sha": "sha-main" }
              },
              {
                "name": "dev",
                "commit": { "sha": "sha-dev" }
              }
            ]
            """)));

        stubFor(get(urlEqualTo("/repos/octocat/forked-repo/branches"))
                .willReturn(okJson("[]")));
    }


    @Test
    void shouldReturnExpectedJsonResponse() throws Exception {

        // WHEN: The consumer calls the endpoint to fetch the repositories.
        // THEN: The response contains the non-fork repository with two branches and their respective commit SHAs.

        String expectedJson = """
        [
          {
            "name": "not-fork-repo",
            "owner": { "login": "octocat" },
            "fork": false,
            "branches": [
              { "name": "main", "sha": "sha-main" },
              { "name": "dev", "sha": "sha-dev" }
            ]
          }
        ]
        """;

        String actualJson = webTestClient.get()
                .uri("/github/octocat/repos")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }
}
