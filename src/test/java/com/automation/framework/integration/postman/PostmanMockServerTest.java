package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

import static com.automation.framework.data.FrameworkConstants.*;
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
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
                .header(X_API_KEY_HEADER, apiKey)
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
        Header xApiKey = new Header(X_API_KEY_HEADER, apiKey);

        Headers headers = new Headers(header, matchHeader, xApiKey);

        given()
                .baseUri(postmanMockServerUrl)
                .headers(headers)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat().statusCode(SC_SUCCESS);
    }


    @Test
    @Order(3)
    public void verifyMultipleHeadersRequestUsingHashMap(){

        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put(HEADER, "value2");
        headers.put(X_MOCK_MATCH_REQUEST_HEADERS, HEADER);
        headers.put(X_API_KEY_HEADER, apiKey);

        given()
                .baseUri(postmanMockServerUrl)
                .headers(headers)
                .when()
                .get("/get")
                .then()
                .log().all()
                .assertThat().statusCode(SC_SUCCESS);
    }


    @Test
    @Order(4)
    public void verifyMultiValueHeadersUsingHeaders(){

        Header header1 = new Header(HEADER, "value1");
        Header header2 = new Header(HEADER, "value2");
        Header header3 = new Header(X_API_KEY_HEADER, apiKey);

        Headers headers = new Headers(header1, header2, header3);

        given()
                .baseUri(postmanMockServerUrl)
                .headers(headers)
                .log().headers()
                .when()
                .get("/get")
                .then()
                .log().body()
                .assertThat().statusCode(SC_SUCCESS).header("responseHeader", "resValue1");
    }


    @Test
    @Order(5)
    public void verifyResponseHeaders(){

        Header header1 = new Header(HEADER, "value1");
        Header header2 = new Header(HEADER, "value2");
        Header header3 = new Header(X_API_KEY_HEADER, apiKey);

        Headers headers = new Headers(header1, header2, header3);

        given()
                .baseUri(postmanMockServerUrl)
                .headers(headers)
                .log().headers()
                .when()
                .get("/get")
                .then()
                .log().body()
                .assertThat()
                .statusCode(SC_SUCCESS)
                .headers("responseHeader", "resValue1",X_RATELIMIT_LIMIT, "120");
    }


    @Test
    @Order(6)
    public void verifyRequestHeaderWithoutXApiKeyReturnsNotFoundStatusCode(){

        given()
                .baseUri(postmanMockServerUrl)
                .header(HEADER, "value1")
                .log().headers()
                .when()
                .get("/get")
                .then()
                .log().body()
                .assertThat()
                .statusCode(SC_NOT_FOUND);
    }



}
