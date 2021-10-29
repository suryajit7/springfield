package com.automation.framework.integration;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanAPITest {


    @Test
    public void simpleRestTest(){
        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-6176c15477601e003b81a9cf-9e15801f58985a5cd0b28187bc0bb39e33")
        .when()
                .get("/workspaces")
        .then()
                .statusCode(200)
                .body("name", is(equalTo("Suryajit")),
                        "email", is(equalTo("suryajit7@gmail.com")));
    }

    @Test
    public void verifyGetStatusCode(){

        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-6176c15477601e003b81a9cf-9e15801f58985a5cd0b28187bc0bb39e33")
        .when()
                .get("/workspaces")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


    @Test
    public void verifyGetResponseCode(){

        given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-6176c15477601e003b81a9cf-9e15801f58985a5cd0b28187bc0bb39e33")
                .when()
                .get("/workspaces")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", hasItem("Team Workspace, MyWorkspace, My Workspace, Test"));
    }

}
