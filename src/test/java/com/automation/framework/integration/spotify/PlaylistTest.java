package com.automation.framework.integration.spotify;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.data.entity.app.spotify.Playlist;
import com.automation.framework.service.SpecBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.hc.core5.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest extends AutomationSuiteApplicationTests {

    private Playlist playlist;
    private final String SPOTIFY_USER_ID = "kuub16j6xd3bluyycbvgzkxxv";
    private final String SPOTIFY_PLAYLIST_ID = "4I4maKtXwef0mtTIZ74GjU";

    @Autowired
    private SpecBuilder specBuilder;


    @Test
    @Order(1)
    public void shouldGetAllExistingSpotifyPlaylists(){

        List<String> playlistIds = given(specBuilder.getRequestSpec())
                .when()
                .pathParam("user_id", SPOTIFY_USER_ID)
                .get("/users/{user_id}/playlists")
                .then().spec(specBuilder.getResponseSpec())
                .assertThat()
                .statusCode(SC_OK)
                .extract().response().jsonPath().getJsonObject("items.id");

        Assertions.assertThat(playlistIds)
                .isNotNull()
                .hasSize(3);

        logger.info(playlistIds);

    }


    @Test
    @Order(2)
    public void shouldUpdateMySpotifyPlaylist(){

        playlist = Playlist.builder()
                .name("MyPlaylist")
                .description("This is my playlist description.")
                ._public(false)
                .build();

        given(specBuilder.getRequestSpec()).body(playlist)
                .when().pathParam("playlistID", SPOTIFY_PLAYLIST_ID).put("/playlists/{playlistID}")
                .then().spec(specBuilder.getResponseSpec()).assertThat().statusCode(SC_OK);

    }


    @Test
    @Order(3)
    public void shouldNotBeAbleToCreatePlaylistWithGivenName(){

        playlist = Playlist.builder()
                .name("")
                .description("This is my playlist description.")
                ._public(false)
                .build();

        given(specBuilder.getRequestSpec())
                .body(playlist)
                .when()
                .pathParam("user_id", SPOTIFY_USER_ID)
                .post("/users/{user_id}/playlists")
                .then()
                .spec(specBuilder.getResponseSpec())
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("error.status", equalTo( SC_BAD_REQUEST),
                        "error.message", equalTo("Missing required field: name"));
    }


    @Test
    @Order(4)
    public void shouldNotBeAbleToCreatePlaylistWithExpiredAccessToken(){

        playlist = Playlist.builder()
                .name("")
                .description("This is my playlist description.")
                ._public(false)
                .build();

        given()
                .baseUri(specBuilder.spotifyUrl)
                .basePath("/v1")
                .header(AUTHORIZATION, specBuilder.decryptService.getSpotifyAccessToken(true))
                .contentType(JSON)
                .log().all()
                .body(playlist)
                .when()
                .pathParam("user_id", SPOTIFY_USER_ID)
                .post("/users/{user_id}/playlists")
                .then()
                .spec(specBuilder.getResponseSpec())
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("error.status", equalTo( SC_UNAUTHORIZED),
                        "error.message", equalTo("The access token expired"));
    }



}
