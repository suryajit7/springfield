package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.util.PropertyDecryptService;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static com.automation.framework.data.Constants.*;
import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This Test class doesn't make use of Spec Builder or follow general design patterns used in this framework.
 * I just wanted to try out few things using postman api.
 */
@Disabled
public class PostmanMockServerTest extends AutomationSuiteApplicationTests {

    private static String apiToken;

    @BeforeEach
    public void setupBeforeTest(){
        apiToken = appCtx.getBeanOfType(PropertyDecryptService.class).getPostmanKey();
    }

    @Test
    @Order(1)
    public void verifyMultipleHeadersRequestUsingHeaders(){
        given()
                .baseUri(postmanMockServerUrl)
                .header(HEADER, "value1")
                .header(X_MOCK_MATCH_REQUEST_HEADERS, HEADER)
                .header(X_API_KEY_HEADER, apiToken)
                .log().headers()
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
        Header xApiKey = new Header(X_API_KEY_HEADER, apiToken);

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
        headers.put(X_API_KEY_HEADER, apiToken);

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
        Header header3 = new Header(X_API_KEY_HEADER, apiToken);

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
        Header header3 = new Header(X_API_KEY_HEADER, apiToken);

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
                .headers("responseHeader", "resValue1",X_RATE_LIMIT_LIMIT, "120");
    }


    @Test
    @Order(6)
    public void verifyRequestHeaderWithoutXApiKey(){

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


    @Test
    @Order(7)
    public void verifyExtractResponseHeaders(){

        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put(HEADER, "value1");
        headers.put(X_MOCK_MATCH_REQUEST_HEADERS, HEADER);
        headers.put(X_API_KEY_HEADER, apiToken);

        Headers responseHeaders = given()
                .baseUri(postmanMockServerUrl).headers(headers)
                .when()
                .get("/get")
                .then()
                .assertThat().statusCode(SC_SUCCESS)
                .extract().headers();

        assertTrue(responseHeaders.get(RESPONSE_HEADER).getName().equalsIgnoreCase(RESPONSE_HEADER), "Verify Header Name: ");
        assertTrue(responseHeaders.get(RESPONSE_HEADER).getValue().equalsIgnoreCase("resValue1"), "Verify Header Value: ");
        assertTrue(responseHeaders.getValue(RESPONSE_HEADER).equalsIgnoreCase("resValue1"), "Verify Header Value: ");

    }


    @Test
    @Order(8)
    public void verifyExtractMultiResponseHeaders(){

        HashMap<String, String> headers = new HashMap<String, String>();

        headers.put(HEADER, "value1");
        headers.put(X_MOCK_MATCH_REQUEST_HEADERS, HEADER);
        headers.put(X_API_KEY_HEADER, apiToken);

        Headers responseHeaders = given()
                .baseUri(postmanMockServerUrl).headers(headers)
                .when()
                .get("/get")
                .then()
                .assertThat().statusCode(SC_SUCCESS)
                .extract().headers();

        assertTrue(responseHeaders.get(MULTI_VALUE_HEADER).getName().equalsIgnoreCase(MULTI_VALUE_HEADER), "Verify Header Name: ");
        Assertions.assertThat(responseHeaders.get(MULTI_VALUE_HEADER).getName()).asString().isEqualTo(MULTI_VALUE_HEADER);

        Assertions.assertThat(responseHeaders.getValues(MULTI_VALUE_HEADER).toArray())
                .hasSize(1)
                .containsAll(List.of("multiResValue1, multiResValue2"))
                .doesNotContain("resValue1");
    }



}
