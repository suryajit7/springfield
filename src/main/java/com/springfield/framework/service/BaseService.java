package com.springfield.framework.service;

import com.springfield.framework.core.Kernel;
import com.springfield.framework.core.config.SpecBuilder;
import com.springfield.framework.data.entity.Playlist;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.springfield.framework.core.auth.AccessTokenManager.generateBearerAccessToken;
import static com.springfield.framework.data.Constants.PATH_PARAM;
import static com.springfield.framework.data.Constants.SPOTIFY_ACCOUNT_URL;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;

@Slf4j
@Service
public class BaseService extends Kernel {

    public Response get(String path){
        return given(SpecBuilder.getRequestSpec())
                .auth().oauth2(generateBearerAccessToken())
                .when()
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }


    public Response get(String path, String pathID){
        return given(SpecBuilder.getRequestSpec())
                .auth().oauth2(generateBearerAccessToken())
                .when()
                .pathParam(PATH_PARAM, pathID)
                .get(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, String pathID, Object payload){
        return given(SpecBuilder.getRequestSpec())
                .auth().oauth2(generateBearerAccessToken())
                .body(payload)
                .pathParam(PATH_PARAM, pathID)
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, Object payload){
        return given(SpecBuilder.getRequestSpec())
                .auth().oauth2(generateBearerAccessToken())
                .body(payload)
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }


    public Response post(String path, HashMap<String, String> formParamsPayload){
        return given()
                .baseUri(SPOTIFY_ACCOUNT_URL)
                .formParams(formParamsPayload)
                .contentType(URLENC)
                .when()
                .post(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }


    public Response put(String path, String pathID, Playlist payload){
        return given(SpecBuilder.getRequestSpec())
                .body(payload)
                .auth().oauth2(generateBearerAccessToken())
                .pathParam(PATH_PARAM, pathID)
                .put(path)
                .then()
                .spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }



}
