package com.automation.framework.util;


import com.automation.framework.core.annotation.LazyAutowired;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.automation.framework.data.Constants.*;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.URLENC;
import static org.apache.hc.core5.http.HttpStatus.SC_SUCCESS;

@Slf4j
@Component
public class SpecBuilder {

    @LazyAutowired
    private PropertyDecryptService decryptService;

    @Value("${app.spotify.url}")
    private String spotifyUrl;

    @Value("${app.spotify.accounts.url}")
    private String spotifyAccountsUrl;

    @Value("${app.postman.url}")
    private String postmanUrl;

    public RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(spotifyUrl)
                .setBasePath("/v1")
                .setContentType(JSON)
                //.addFilter(new AllureRestAssured())
                .log(ALL).build();
    }

    public ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .log(ALL).build();
    }

    public RequestSpecification getPostmanRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(postmanUrl)
                .addHeader(X_API_KEY_HEADER, decryptService.getPostmanKey())
                .setContentType(JSON)
                //.addFilter(new AllureRestAssured())
                .log(ALL).build();
    }

    public ResponseSpecification getPostmanResponseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_SUCCESS)
                .expectContentType(JSON)
                .log(ALL).build();
    }

    public RequestSpecification getAccountSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(spotifyAccountsUrl)
                .addFormParams(getFormParams())
                .setContentType(URLENC)
                //.addFilter(new AllureRestAssured())
                .log(ALL).build();
    }

    public HashMap<String, String> getFormParams() {

        HashMap<String, String> formParams = new HashMap<>();
        formParams.clear();
        formParams.put(CLIENT_ID, SPOTIFY_CLIENT_ID);
        formParams.put(CLIENT_SECRET, decryptService.getSpotifyClientSecret());
        formParams.put(REFRESH_TOKEN, decryptService.getSpotifyRefreshToken());
        formParams.put(GRANT_TYPE, REFRESH_TOKEN);

        return formParams;
    }

}
