package com.automation.framework.integration.postman;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;

import static com.automation.framework.data.FrameworkConstants.X_API_KEY_HEADER;
import static io.restassured.RestAssured.with;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@TestInstance(PER_CLASS)
public class PostmanRequestSpecificationTest extends AutomationSuiteApplicationTests {

    @Value("${app.api.key}")
    protected String apiKey;

    @Value("${app.postman.url}")
    protected String postmanUrl;

    @Value("${app.postman.mock.url}")
    protected String postmanMockServerUrl;

    private static RequestSpecification requestSpecification;

    @BeforeAll
    public void beforeClassSetup(){
        requestSpecification = with()
                .baseUri(postmanUrl)
                .header(X_API_KEY_HEADER, apiKey)
                .log().all();
    }


    @Test
    @Order(1)
    public void verifyRequestSpecificationWithStatusCode(){

        requestSpecification
                .when()
                .get("/workspaces")
                .then()
                .assertThat().statusCode(SC_SUCCESS);
    }


    @Test
    @Order(2)
    public void verifyResponseBody(){

        Response response = requestSpecification.get("/workspaces")
                .then().log().all().extract().response();

        Assertions.assertThat(response.statusCode())
                .isEqualTo(SC_SUCCESS);
    }


}
