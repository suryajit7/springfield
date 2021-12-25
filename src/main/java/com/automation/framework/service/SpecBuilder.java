package com.automation.framework.service;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@Slf4j
@Component
public class SpecBuilder {

    @Value("${app.spotify.url}")
    private String spotifyUrl;

    public RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(spotifyUrl)
                .setBasePath("/v1")
                .setContentType(JSON)
                .log(ALL).build();
    }

    public ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .log(ALL).build();
    }

}
