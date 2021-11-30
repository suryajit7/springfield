package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.data.entity.app.postman.Postman;

import com.automation.framework.util.FileReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static com.automation.framework.data.Constants.X_API_KEY_HEADER;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PostmanRestAPITest extends AutomationSuiteApplicationTests {

    @Autowired
    private FileReader fileReader;

    private String workspaceId;
    private Postman payLoad;

    @BeforeAll
    public void beforeClassSetup() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(postmanUrl)
                .addHeader(X_API_KEY_HEADER, apiKey)
                .setContentType(JSON)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(SC_SUCCESS)
                .expectContentType(JSON)
                .log(ALL)
                .build();

        payLoad = fileReader.readJsonFile("postmanWorkspaceData.json")
                .getPostmen().get(0);
    }


    @Test
    @Order(1)
    public void verifyRequestSpecificationWithStatusCode(){

        get("/workspaces")
                .then().assertThat().statusCode(SC_SUCCESS);
    }


    @Test
    @Order(2)
    public void verifyRequestSpecificationWithResponseBody(){

        Response response = get("/workspaces")
                .then().log().all().extract().response();

        Assertions.assertThat(response.statusCode())
                .isEqualTo(SC_SUCCESS);
    }


    @Test
    @Order(3)
    public void verifyQueryingRequestSpecification(){

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier
                .query(requestSpecification);

        Assertions.assertThat(queryableRequestSpecification.getBaseUri())
                .isEqualTo(postmanUrl)
                .isNotBlank();
    }


    @Test
    @Order(4)
    public void verifyResponseSpecification(){

        Response response = get("/workspaces")
                .then().spec(responseSpecification)
                .extract().response();

        Assertions.assertThat(response.path("workspaces[0].name").toString())
                .isNotBlank()
                .isEqualTo("Team Workspace");

    }


    @Test
    @Order(5)
    public void validatePostRequest() {

        Response response = with()
                .body(payLoad)
                .post("/workspaces")
                .then()
                .extract().response();

        assertThat(response.<String>path("workspace.name"), containsString("testWorkspace"));

        workspaceId = response.<String>path("workspace.id");
    }


    @Test
    @Order(6)
    public void validatePutRequest() {

        payLoad.getWorkspace().setName("Modified Workspace");

        Response response = with()
                .body(payLoad)
                .pathParam("workspaceId", workspaceId)
                .put("/workspaces/{workspaceId}")
                .then()
                .extract().response();

        assertThat(response.<String>path("workspace.name"), containsString("Modified Workspace"));

        Assertions.assertThat(response.jsonPath().getJsonObject("workspace.id").toString())
                .isNotNull()
                .isEqualTo(workspaceId);
    }


    @Test
    @Order(7)
    public void validateDeleteRequest() {

        Response response = with()
                .pathParam("workspaceId", workspaceId)
                .delete("/workspaces/{workspaceId}")
                .then()
                .assertThat().statusCode(SC_SUCCESS)
                .extract().response();

        Assertions.assertThat(response.jsonPath().getJsonObject("workspace.id").toString())
                .isNotNull()
                .isEqualTo(workspaceId);
    }


    @Test
    @Order(8)
    public void validatePostRequestWithNestedJson() {

        HashMap<String, Object> mainObject = new HashMap<String, Object>();
        HashMap<String, String> nestedObject = new HashMap<String, String>();

        nestedObject.put("name", "Nested Workspace");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Creating a Nested Workspace");
        mainObject.put("workspace", nestedObject);

        Response response = with()
                .body(mainObject)
                .post("/workspaces")
                .then()
                .assertThat().statusCode(SC_SUCCESS)
                .extract().response();

        Assertions.assertThat(response.jsonPath().getJsonObject("workspace.name").toString())
                .isNotNull()
                .isEqualTo("Nested Workspace");

        workspaceId = response.<String>path("workspace.id");

        with()
                .pathParam("workspaceId", workspaceId)
                .delete("/workspaces/{workspaceId}")
                .then()
                .assertThat().statusCode(SC_SUCCESS);
    }


}
