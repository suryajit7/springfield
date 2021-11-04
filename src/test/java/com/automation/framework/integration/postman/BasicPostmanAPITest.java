package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

import static com.automation.framework.data.FrameworkConstants.X_API_KEY_HEADER;
import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.logConfig;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class BasicPostmanAPITest extends AutomationSuiteApplicationTests {

    @Value("${app.api.key}")
    protected String apiKey;

    @Value("${app.postman.url}")
    protected String postmanUrl;


    @Test
    @Order(1)
    public void verifySimpleRestTest(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS);
    }

    @Test
    @Order(2)
    public void verifyGetStatusCode(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat().statusCode(SC_SUCCESS);
    }

    @Test
    @Order(3)
    public void verifyGetResponseCode(){
        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat().statusCode(SC_SUCCESS)
                .body("workspaces.name", is(not(emptyArray())),
                        "workspaces.name", hasItem("MyWorkspace"),
                        "workspaces[0].name", equalTo("Team Workspace"),
                        "workspaces.name", hasSize(greaterThan(3)),
                        "workspaces[0]", hasKey("type"),
                        "workspaces[0]", hasValue("Team Workspace"),
                        "workspaces[0]", hasEntry("name", "Team Workspace"));

    }

    @Test
    @Order(4)
    public void verifyExtractResponse(){
        //Example 1
        Response response = given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS)
                .extract().response();

        String jsonPath = new JsonPath(response.asString()).getString("workspaces[0].name");
        System.out.println("workspace name = " + jsonPath);


        //Example 2
        String workspaceName = given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS)
                .extract().response().path("workspaces[0].name");

        System.out.println("workspace name = " + workspaceName);

        assertThat(workspaceName, equalTo("Team Workspace"));
    }

    @Test
    @Order(5)
    public void verifyLoggingForRequestAndResponse(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .statusCode(SC_SUCCESS);
    }

    @Test
    @Order(6)
    public void verifyLoggingIfValidationFails(){

        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS);
    }

    @Test
    @Order(7)
    public void verifyLoggingBlackListedHeaders(){

        Set blackListHeaders = Set.of(X_API_KEY_HEADER, "Accept");
        given()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .config(config().logConfig(logConfig().blacklistHeaders(blackListHeaders)))
                .log().all()
                .when()
                .get("/workspaces")
                .then()
                .statusCode(SC_SUCCESS);
    }



}
