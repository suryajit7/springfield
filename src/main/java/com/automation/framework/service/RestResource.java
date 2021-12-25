package com.automation.framework.service;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.data.entity.app.spotify.Playlist;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
public class RestResource extends BaseService {

    @LazyAutowired
    private TokenManager tokenManager;

    public Response get(String path, String id){

        return given(getRequestSpec())
                .header(AUTHORIZATION, tokenManager.getAccessToken())
                .when()
                .pathParam("_id", id)
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public Response post(String path, String id, Playlist requestPlaylist, Boolean expiredToken){

        return given(getRequestSpec())
                .body(requestPlaylist)
                .header(AUTHORIZATION, expiredToken ? decryptService.getSpotifyAccessToken(expiredToken) : tokenManager.getAccessToken())
                .when()
                .pathParam("_id", id)
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public Response update(String path, String id, Playlist requestPlaylist){

        return given(getRequestSpec())
                .body(requestPlaylist)
                .header(AUTHORIZATION, tokenManager.getAccessToken())
                .when()
                .pathParam("_id", id)
                .put(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }



}
