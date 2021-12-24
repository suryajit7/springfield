package com.automation.framework.service.api;

import com.automation.framework.data.entity.app.spotify.Playlist;
import com.automation.framework.service.BaseService;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Slf4j
@Component
public class PlaylistService extends BaseService {

    public Response getPlaylist(String playlistID){
        return given(getRequestSpec())
                .when()
                .pathParam("playlist_id", playlistID)
                .get("/playlists/{playlist_id}")
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public Response get(String userID){
        return given(getRequestSpec())
                .when()
                .pathParam("user_id", userID)
                .get("/users/{user_id}/playlists")
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public Response post(String userID, Playlist requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .when()
                .pathParam("user_id", userID)
                .post("/users/{user_id}/playlists")
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public Response post(String userID, Playlist requestPlaylist, Boolean expiredToken){
        return given(getRequestSpec(expiredToken))
                .body(requestPlaylist)
                .when()
                .pathParam("user_id", userID)
                .post("/users/{user_id}/playlists")
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public Response update(String playlistID, Playlist requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .when()
                .pathParam("playlist_id", playlistID)
                .put("/playlists/{playlist_id}")
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }



}
