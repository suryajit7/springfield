package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.util.PropertyDecryptService;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.Set;

import static com.automation.framework.data.Constants.X_API_KEY_HEADER;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;


public class PostmanAPITestScenarios extends AutomationSuiteApplicationTests {

    @Autowired
    private PropertyDecryptService decryptService;

    @Value("${app.postman-echo.url}")
    protected String postmanEchoUrl;

    @Value("${app.reqres.url}")
    protected String reqresUrl;


    @Test
    @Order(1)
    public void verifyLoggingForRequestAndResponse(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, decryptService.getPostmanKey())
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(SC_SUCCESS)
                .body(matchesJsonSchemaInClasspath("schemas/PostmanJsonSchema.json"));
    }

    @Test
    @Order(2)
    public void verifyLoggingIfValidationFails(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, decryptService.getPostmanKey())
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS);
    }

    @Test
    @Order(3)
    public void verifyLoggingBlackListedHeaders(){

        Set blackListHeaders = Set.of(X_API_KEY_HEADER, "Accept");
        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, decryptService.getPostmanKey())
                .config(config().logConfig(logConfig().blacklistHeaders(blackListHeaders)))
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS);
    }


    @Test
    @Order(4)
    public void verifyMultipartFormDataInPostRequest(){

        given()
                .baseUri(postmanEchoUrl)
                .multiPart("ping1", "pong")
                .multiPart("ping2", "pong")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(SC_SUCCESS);
    }


    @Test
    @Order(5)
    public void verifyUploadFileMultipartFormData(){
        String attributes = "{\"name\":\"cv\"}";
        given()
                .baseUri(postmanEchoUrl)
                .multiPart("file", new File(pathFinder.getFilePathForFile("UserTestData.txt").toString()))
                .multiPart("attributes", attributes, "application/json")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .assertThat()
                .statusCode(SC_SUCCESS);
    }


    @Test
    @Order(6)
    public void verifyDownloadFileMultipartFormData() throws IOException {
        String attributes = "{\"name\":\"cv\"}";
        InputStream data = given()
                .baseUri("https://raw.githubusercontent.com")
                .log().all()
                .when()
                        .get("/appium/appium/master/sample-code/apps/ApiDemos-debug.apk")
                .then()
                .log().all()
                .extract().response().asInputStream();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[data.available()];
        data.read(bytes);
        os.write(bytes);
        os.close();
    }

    @Test
    @Order(7)
    public void verifyFilterMethodInRequestAndResponse(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, decryptService.getPostmanKey())
                .filter(new RequestLoggingFilter(BODY))
                .filter(new ResponseLoggingFilter(STATUS))
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(SC_SUCCESS)
                .body(matchesJsonSchemaInClasspath("schemas/PostmanJsonSchema.json"));
    }

    @Test
    @Order(8)
    public void verifyFilterMethodInLoggingForRequestAndResponse() throws FileNotFoundException {
        PrintStream fileOutput = new PrintStream(new File("api-test.log"));
        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, decryptService.getPostmanKey())
                .filter(new RequestLoggingFilter(fileOutput))
                .filter(new ResponseLoggingFilter(fileOutput))
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(SC_SUCCESS)
                .body(matchesJsonSchemaInClasspath("schemas/PostmanJsonSchema.json"));
    }



}
