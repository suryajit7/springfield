package com.automation.framework.service;

import com.automation.framework.core.Kernel;
import com.automation.framework.core.annotation.LazyService;
import com.automation.framework.data.entity.spotify.Playlist;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static com.automation.framework.util.AccessTokenProvider.getAccessToken;
import static io.restassured.RestAssured.given;

@Slf4j
@LazyService
public class BaseService extends Kernel {

    public static final String PATH_PARAM = "path_param";

    public Response get(String path, String id){
        return given(specBuilder.getRequestSpec())
                .auth().oauth2(getAccessToken())
                .when()
                .pathParam(PATH_PARAM, id)
                .get(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }

    public Response get(String path){

        return given(specBuilder.getPostmanRequestSpec())
                .when()
                .get(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }

    public Response post(String path, String id, Playlist requestPlaylist){
        return given(specBuilder.getRequestSpec())
                .body(requestPlaylist)
                .auth().oauth2(getAccessToken())
                .pathParam(PATH_PARAM, id)
                .post(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }

    public Response update(String path, String id, Playlist requestPlaylist){
        return given(specBuilder.getRequestSpec())
                .body(requestPlaylist)
                .auth().oauth2(getAccessToken())
                .when()
                .pathParam(PATH_PARAM, id)
                .put(path)
                .then()
                .spec(specBuilder.getResponseSpec())
                .extract().response();
    }


    //TODO: Modify this request as common post
    public Response post(String path, Object requestPayload){
        return given(specBuilder.getPostmanRequestSpec())
                .body(requestPayload)
                .post(path)
                .then().spec(specBuilder.getPostmanResponseSpec())
                .extract().response();
    }

    //TODO: Modify this request as common put
    public Response put(String path, String id, Object requestPayload){
        return given(specBuilder.getPostmanRequestSpec())
                .body(requestPayload)
                .pathParam(PATH_PARAM, id)
                .put(path)
                .then().spec(specBuilder.getPostmanResponseSpec())
                .extract().response();
    }

    //TODO: Modify this request as common delete
    public Response delete(String path, String id){
        return given(specBuilder.getPostmanRequestSpec())
                .pathParam(PATH_PARAM, id)
                .delete(path)
                .then().spec(specBuilder.getPostmanResponseSpec())
                .extract().response();
    }


}
