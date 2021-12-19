package com.automation.framework.integration.google;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

public class GmailAPITest extends AutomationSuiteApplicationTests {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    private String access_token= "Bearer ya29.A0ARrdaM-dZwt9fbVZ_IdErrkPrcJucoKgeT90pRYcpvagsvknRHhYdR24SZ2U-fpwYI4S5bJQYwsoPbvhIsUBd5oDNRtRWAa6UWXTDCdn3j16s_ahYmcb2XXazKmkLE-fvw0ywsOfjrzRifxxlLFbv68LG5Tg";

    @BeforeAll
    public void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://gmail.googleapis.com")
                .addHeader("Authorize", access_token)
                .setContentType(JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(SC_SUCCESS)
                .expectContentType(JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void getUserProfile(){

        given(requestSpecification)
                .basePath("/gmail/v1")
                .pathParam("userid", "automation.suite7@gmail.om")
                .when()
                .get("/users/{userid}/profile")
                .then().assertThat().statusCode(SC_SUCCESS);

    }
}
