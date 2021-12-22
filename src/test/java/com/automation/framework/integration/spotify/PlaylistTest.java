package com.automation.framework.integration.spotify;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.automation.framework.data.Constants.AUTHORIZE;
import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

public class PlaylistTest extends AutomationSuiteApplicationTests {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    //TODO- Encrypt the below token.
    private final String access_token= "Bearer BQARoyhr3ISnAEYXagAJZUXhhSPapy_0TOggQOugsi3YBRXMSiH4Wkt_hNpF3257bI9r1jikQf-8rSEdMJiitiaghXl2E-2JF6w43hjwenaEhe3UdtD3poK_3Vv-FGqBXVBoPkl-CPY56haLJbLdbNQQfuzXcfO-MsgAdv17zUdB5qwBheRM5s2BtRz1LJ5jMOEOjtsJpu6tTPvfBzXwvy16IolwmfGweuVCphUCiUeP";


    @BeforeAll
    public void beforeClass() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("")
                .setBasePath("/v1")
                .addHeader(AUTHORIZE, access_token)
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
