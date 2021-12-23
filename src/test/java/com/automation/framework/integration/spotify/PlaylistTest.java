package com.automation.framework.integration.spotify;

import com.automation.framework.AutomationSuiteApplicationTests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.apache.hc.core5.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest extends AutomationSuiteApplicationTests {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    private final String SPOTIFY_USER_ID = "kuub16j6xd3bluyycbvgzkxxv";
    private final String SPOTIFY_PLAYLIST_ID = "4I4maKtXwef0mtTIZ74GjU";

    @Value("${app.spotify.url}")
    private String spotifyUrl;

    @BeforeAll
    public void beforeClass() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(spotifyUrl)
                .setBasePath("/v1")
                .addHeader(AUTHORIZATION, decryptService.getSpotifyAccessToken(false))
                .setContentType(JSON)
                .log(ALL).build();

        responseSpec = new ResponseSpecBuilder()
                .log(ALL).build();
    }


    @Test
    @Order(1)
    public void shouldGetAllExistingSpotifyPlaylists(){

        List<String> playlistIds = given(requestSpec)
                .when()
                .pathParam("user_id", SPOTIFY_USER_ID)
                .get("/users/{user_id}/playlists")
                .then().spec(responseSpec)
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

        String payLoad = "{\n" +
                "    \"name\": \"MyPlaylist\",\n" +
                "    \"description\": \"This is my playlist description.\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpec).body(payLoad)
                .when().pathParam("playlistID", SPOTIFY_PLAYLIST_ID).put("/playlists/{playlistID}")
                .then().spec(responseSpec).assertThat().statusCode(SC_OK);

    }


    @Test
    @Order(3)
    public void shouldNotBeAbleToCreatePlaylistWithGivenName(){

        String payLoad = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"This is my playlist description.\",\n" +
                "    \"public\": false\n" +
                "}";

        given(requestSpec)
                .body(payLoad)
                .when()
                .pathParam("user_id", SPOTIFY_USER_ID)
                .post("/users/{user_id}/playlists")
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("error.status", equalTo( SC_BAD_REQUEST),
                        "error.message", equalTo("Missing required field: name"));
    }


    @Test
    @Order(4)
    public void shouldNotBeAbleToCreatePlaylistWithExpiredAccessToken(){

        String payLoad = "{\n" +
                "    \"name\": \"My New Playlist\",\n" +
                "    \"description\": \"This is my playlist description.\",\n" +
                "    \"public\": false\n" +
                "}";

        given()
                .baseUri(spotifyUrl)
                .basePath("/v1")
                .header(AUTHORIZATION, decryptService.getSpotifyAccessToken(true))
                .contentType(JSON)
                .log().all()
                .body(payLoad)
                .when()
                .pathParam("user_id", SPOTIFY_USER_ID)
                .post("/users/{user_id}/playlists")
                .then()
                .spec(responseSpec)
                .assertThat()
                .statusCode(SC_UNAUTHORIZED)
                .body("error.status", equalTo( SC_UNAUTHORIZED),
                        "error.message", equalTo("The access token expired"));
    }



}
