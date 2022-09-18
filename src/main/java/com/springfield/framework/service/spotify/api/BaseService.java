package com.springfield.framework.service.spotify.api;

import com.springfield.framework.core.Kernel;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.springfield.framework.data.Constants.PATH_PARAM;
import static io.restassured.RestAssured.given;

@Slf4j
@Service
public class BaseService extends Kernel {

    public Response get(String path){
        return given(specBuilder.getRequestSpec())
                //.auth().oauth2(path.contains(LOGIN_ROUTE) ? getAccessToken() : generateBearerAccessToken())
                .when()
                .get(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }


    public Response get(String path, String pathID){
        return given(specBuilder.getRequestSpec())
                //.auth().oauth2(generateBearerAccessToken())
                .when()
                .pathParam(PATH_PARAM, pathID)
                .get(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, String pathID, Object payload){
        return given(specBuilder.getRequestSpec())
                //.auth().oauth2(generateBearerAccessToken())
                .body(payload)
                .pathParam(PATH_PARAM, pathID)
                .post(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, Object payload){
        return given(specBuilder.getRequestSpec())
                //.auth().oauth2(generateBearerAccessToken())
                .body(payload)
                .post(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }



}
