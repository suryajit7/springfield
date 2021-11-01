package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static com.automation.framework.data.FrameworkConstants.HEADER;
import static com.automation.framework.data.FrameworkConstants.X_MOCK_MATCH_REQUEST_HEADERS;
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

public class PostmanMockServerTest extends AutomationSuiteApplicationTests {

    @Value("${app.api.key}")
    private String apiKey;

    @Value("${app.postman.mock.url}")
    private String postmanMockServerUrl;

    @Test
    @Order(1)
    public void verifyMultipleHeadersRequestUsingHeaders(){
        given()
                .baseUri(postmanMockServerUrl)
                .header(HEADER, "value1")
                .header(X_MOCK_MATCH_REQUEST_HEADERS, HEADER)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat().statusCode(SC_SUCCESS);

    }


    @Test
    @Order(2)
    public void verifyMultipleHeadersRequestUsingMap(){

        Header header = new Header(HEADER, "value2");
        Header matchHeader = new Header(X_MOCK_MATCH_REQUEST_HEADERS, HEADER);

        Headers headers = new Headers(header, matchHeader);

        given()
                .baseUri(postmanMockServerUrl)
                .headers(headers)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat().statusCode(200);

    }



}
