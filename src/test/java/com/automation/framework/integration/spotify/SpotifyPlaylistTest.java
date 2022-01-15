package com.automation.framework.integration.spotify;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.data.entity.spotify.Playlist;
import com.automation.framework.service.api.spotify.PlaylistService;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SpotifyPlaylistTest extends AutomationSuiteApplicationTests {

    public static final String SPOTIFY_USER_ID = "kuub16j6xd3bluyycbvgzkxxv";
    public static final String SPOTIFY_PLAYLIST_ID = "4I4maKtXwef0mtTIZ74GjU";
    private Playlist requestPlaylist;


    @LazyAutowired
    private PlaylistService playlistService;

    @BeforeAll
    public void setupTestData(){
        requestPlaylist = new Playlist().getPlaylist(faker.music().genre(), faker.artist().name(), false);
    }

    @Test
    @Order(1)
    public void shouldGetAllExistingSpotifyPlaylists(){

        List<String> playlistIds = playlistService.getUserId(SPOTIFY_USER_ID)
                .path("items.id");

        logger.info(playlistIds);

        Assertions.assertThat(playlistIds)
                .isNotNull();
    }


    @Test
    @Order(2)
    public void shouldUpdateMySpotifyPlaylist(){

        playlistService.update(SPOTIFY_PLAYLIST_ID, requestPlaylist)
                .then().assertThat()
                .statusCode(SC_OK);

        Response response = playlistService.getPlaylist(SPOTIFY_PLAYLIST_ID);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(), equalTo(SC_OK));

        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.getIsPublic(), equalTo(requestPlaylist.getIsPublic()));

    }


    @Test
    @Order(3)
    public void shouldNotBeAbleToCreatePlaylistWithGivenName(){

        requestPlaylist.setName("");

        Response response = playlistService.post(SPOTIFY_USER_ID, requestPlaylist,false);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(), equalTo(SC_BAD_REQUEST));

        assertThat(responsePlaylist.getError().getStatus(), equalTo(SC_BAD_REQUEST));
        assertThat(responsePlaylist.getError().getMessage(), equalTo("Missing required field: name"));
    }


    @Test
    @Order(4)
    public void shouldNotBeAbleToCreatePlaylistWithExpiredAccessToken() {

        requestPlaylist.setName(faker.music().genre());

        Response response = playlistService.post(SPOTIFY_USER_ID, requestPlaylist, true);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(), equalTo(SC_UNAUTHORIZED));

        assertThat(responsePlaylist.getError().getStatus(), equalTo(SC_UNAUTHORIZED));
        assertThat(responsePlaylist.getError().getMessage(), equalTo("Invalid access token"));
    }
}
