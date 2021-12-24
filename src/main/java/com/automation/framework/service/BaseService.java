package com.automation.framework.service;


import com.automation.framework.core.Kernel;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@Slf4j
@Component
public class BaseService extends Kernel {

    public RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(spotifyUrl)
                .setBasePath("/v1")
                .setContentType(JSON)
                .log(ALL).build();
    }

/*    public RequestSpecification getRequestSpec(Boolean expiredToken){
        return new RequestSpecBuilder()
                .setBaseUri(spotifyUrl)
                .setBasePath("/v1")
                .addHeader(AUTHORIZATION, decryptService.getSpotifyAccessToken(expiredToken))
                .setContentType(JSON)
                .log(ALL).build();
    }*/

    public ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .log(ALL).build();
    }

}
