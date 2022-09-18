package com.springfield.framework.core.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.springfield.framework.data.Constants.SPOTIFY_API_ENDPOINT;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;


public class SpecBuilder {


    public static RequestSpecification getRequestSpec(){
        RequestSpecification requestSpec = null;
        return requestSpec = new RequestSpecBuilder()
                .setBaseUri(SPOTIFY_API_ENDPOINT)
                .setBasePath("/v1")
                .setContentType(JSON)
                .log(ALL).build();
    }


    public static ResponseSpecification getResponseSpec(){
        ResponseSpecification responseSpec = null;
        return responseSpec = new ResponseSpecBuilder()
                .log(ALL).build();
    }
}
