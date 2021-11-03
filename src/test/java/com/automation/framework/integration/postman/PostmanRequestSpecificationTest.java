package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.data.entity.BaseEntity;
import com.automation.framework.data.entity.app.postman.Workspace;
import com.automation.framework.util.FileReader;
import com.automation.framework.util.converter.StringToJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.openqa.selenium.json.Json;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.automation.framework.data.FrameworkConstants.X_API_KEY_HEADER;
import static com.automation.framework.util.converter.StringToJson.*;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostmanRequestSpecificationTest extends AutomationSuiteApplicationTests {

    @Autowired
    private FileReader fileReader;

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

        BaseEntity payLoad = fileReader.readJsonFile("postmanWorkspaceData.json");

        Response response = with()
                .body(payLoad.getPostmen().get(0))
                .post("/workspaces")
                .then()
                .extract().response();

        assertThat(response.<String>path("workspace.name"), equalTo("testWorkspace"));

        System.out.println(toJson(response.body().toString(), Workspace.class).getId());
    }


    @Test
    @Order(6)
    public void validatePutRequest() {

        BaseEntity payLoad = fileReader.readJsonFile("postmanWorkspaceData.json");

        Response response = with()
                .body(payLoad.getPostmen().get(0))
                .put("/workspaces/b59b5d87-cda7-46a5-a896-13b67555f98d")
                .then()
                .extract().response();

        assertThat(response.<String>path("workspace.name"), equalTo("testWorkspace"));

        Assertions.assertThat(response.jsonPath().getJsonObject("workspace.id").toString())
                .isNotNull()
                .isEqualTo("b59b5d87-cda7-46a5-a896-13b67555f98d");
    }


}
