package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.builder.*;

import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static com.automation.framework.data.FrameworkConstants.X_API_KEY_HEADER;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.*;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

public class PostmanPostRequestTest extends AutomationSuiteApplicationTests {

    @BeforeAll
    public void beforeClassSetup(){

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(postmanUrl)
                .addHeader(X_API_KEY_HEADER, apiKey)
                .setContentType(JSON)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(SC_SUCCESS)
                .expectContentType(JSON)
                .log(LogDetail.ALL)
                .build();

    }


    @Test
    @Order(1)
    public void validatePostRequest(){

        String payLoad = "{\n" +
                "\"workspace\":{\n" +
                "    \"name\": \"testWorkspace\",\n" +
                "    \"type\": \"personal\",\n" +
                "    \"description\": \"Rest Assured created this.\"\n" +
                "}\n" +
                "}";

        Response response = with()
                .body(payLoad)
                .post("/workspaces");

        assertThat(response.<String>path("workspace.name"), equalTo("testWorkspace"));
    }

}
