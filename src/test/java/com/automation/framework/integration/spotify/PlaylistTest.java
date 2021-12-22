package com.automation.framework.integration.spotify;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static com.automation.framework.data.Constants.AUTHORIZE;
import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

public class PlaylistTest extends AutomationSuiteApplicationTests {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @Value("${app.spotify.url}")
    private String spotifyUrl;

    @BeforeAll
    public void beforeClass() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(spotifyUrl)
                .setBasePath("/v1")
                .addHeader(AUTHORIZE, decryptService.getSpotifyAccessToken())
                .setContentType(JSON)
                .log(ALL).build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(SC_SUCCESS)
                .expectContentType(JSON)
                .log(ALL).build();
    }

    @Test
    public void getUserProfile(){

        given(requestSpecification)
                .basePath("/gmail/v1")
                .pathParam("userid", "automation.suite7@gmail.om")
                .when()
                .get("/users/{userid}/profile")
                .then().assertThat().statusCode(SC_SUCCESS);

    }
}
