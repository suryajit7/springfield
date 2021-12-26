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

    public static final String RESOURCE_ID = "resource_Id";

    @LazyAutowired
    private TokenManager tokenManager;

    public Response get(String path, String id){

        return given(tokenManager.getRequestSpec())
                .header(AUTHORIZATION, tokenManager.generateAccessToken())
                .when()
                .pathParam("resource_Id", id)
                .get(path)
                .then()
                .spec(tokenManager.getResponseSpec())
                .extract().response();
    }

    public Response get(String path){

        return given(tokenManager.getPostmanRequestSpec())
                .when()
                .get(path)
                .then()
                .spec(tokenManager.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, String id, Playlist requestPlaylist, Boolean expiredToken){

        return given(tokenManager.getRequestSpec())
                .body(requestPlaylist)
                .header(AUTHORIZATION, expiredToken ? decryptService.getSpotifyAccessToken(expiredToken) : tokenManager.generateAccessToken())
                .pathParam(RESOURCE_ID, id)
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


    //TODO: Modify this request as common post
    public Response post(String path, Object requestPayload){
        return given(tokenManager.getPostmanRequestSpec())
                .body(requestPayload)
                .post(path)
                .then().spec(tokenManager.getPostmanResponseSpec())
                .extract().response();
    }

    //TODO: Modify this request as common put
    public Response put(String path, String id, Object requestPayload){
        return given(tokenManager.getPostmanRequestSpec())
                .body(requestPayload)
                .pathParam(RESOURCE_ID, id)
                .put(path)
                .then().spec(tokenManager.getPostmanResponseSpec())
                .extract().response();
    }

    //TODO: Modify this request as common delete
    public Response delete(String path, String id){
        return given(tokenManager.getPostmanRequestSpec())
                .pathParam(RESOURCE_ID, id)
                .delete(path)
                .then().spec(tokenManager.getPostmanResponseSpec())
                .extract().response();
    }


}
