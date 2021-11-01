package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

public class PostmanMockServerTest extends AutomationSuiteApplicationTests {

    @Value("${app.api.key}")
    private String apiKey;

    private String postmanMockServerUrl = "https://2cddbad3-066d-4d33-93a0-eefb23ab29ec.mock.pstmn.io";

    @Test
    @Order(1)
    public void verifyMockServerForMultipleHeaders(){
        given()
                .baseUri(postmanMockServerUrl)
                .header("X-Api-Key", apiKey)
                .header("header", "value1")
                .header("x-mock-match-request-headers", "header")
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat().statusCode(SC_SUCCESS);


    }



}
