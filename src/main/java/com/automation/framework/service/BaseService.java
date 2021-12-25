package com.automation.framework.service;

import com.automation.framework.core.Kernel;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.core.setup.TokenManager;
import com.automation.framework.data.entity.app.spotify.Playlist;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
import static org.apache.hc.core5.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Component
public class BaseService extends Kernel {

    @LazyAutowired
    private TokenManager tokenManager;




    public Response get(String path, String id){

        return given(tokenManager.getRequestSpec())
                .header(AUTHORIZATION, tokenManager.generateAccessToken())
                .when()
                .pathParam("_id", id)
                .get(path)
                .then()
                .spec(tokenManager.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, String id, Playlist requestPlaylist, Boolean expiredToken){

        return given(tokenManager.getRequestSpec())
                .body(requestPlaylist)
                .header(AUTHORIZATION, expiredToken ? decryptService.getSpotifyAccessToken(expiredToken) : tokenManager.generateAccessToken())
                .when()
                .pathParam("_id", id)
                .post(path)
                .then()
                .spec(tokenManager.getResponseSpec())
                .extract().response();
    }

    public Response postAccount(){

        Response response = given(tokenManager.getAccountSpec())
                .when().post("/api/token")
                .then().spec(tokenManager.getResponseSpec())
                .extract().response();

        assertThat(response.statusCode())
                .isNotNull()
                .isEqualTo(SC_OK)
                .withFailMessage("Renew token failed!!!")
                .descriptionText();

        return response;
    }

    public Response update(String path, String id, Playlist requestPlaylist){

        return given(tokenManager.getRequestSpec())
                .body(requestPlaylist)
                .header(AUTHORIZATION, tokenManager.generateAccessToken())
                .when()
                .pathParam("_id", id)
                .put(path)
                .then()
                .spec(tokenManager.getResponseSpec())
                .extract().response();
    }



}
