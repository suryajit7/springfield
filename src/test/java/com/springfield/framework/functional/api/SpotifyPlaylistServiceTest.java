package com.springfield.framework.functional.api;

import com.springfield.framework.SpringfieldApplicationTests;
import com.springfield.framework.core.annotation.LazyAutowired;
import com.springfield.framework.data.entity.Playlist;
import com.springfield.framework.service.api.PlaylistService;
import com.springfield.framework.util.testhelper.AssertWebService;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.springfield.framework.data.Constants.BLANK;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Disabled
public class SpotifyPlaylistServiceTest extends SpringfieldApplicationTests {

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

        List<String> playlistIds = playlistService.getPlaylistIDsForGivenUserID(SPOTIFY_USER_ID).path("items.id");

        System.out.println(playlistIds);

        Assertions.assertThat(playlistIds)
                .isNotNull();
    }


    @Test
    @Order(2)
    public void shouldUpdateMySpotifyPlaylist(){

        Response response1 = playlistService.update(SPOTIFY_PLAYLIST_ID, requestPlaylist);
        AssertWebService.assertThat(response1)
                .hasValidStatusCode(SC_OK);

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

        requestPlaylist.setName(BLANK);

        Response response = playlistService.post(SPOTIFY_USER_ID, requestPlaylist);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(), equalTo(SC_BAD_REQUEST));

        assertThat(responsePlaylist.getError().getStatus(), equalTo(SC_BAD_REQUEST));
        assertThat(responsePlaylist.getError().getMessage(), equalTo("Missing required field: name"));
    }


    @Test
    @Order(4)
    public void shouldNotBeAbleToCreatePlaylistWithExpiredAccessToken() {

/*        requestPlaylist.setName(faker.music().genre());

        Response response = playlistService.post(SPOTIFY_USER_ID, requestPlaylist);
        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(response.statusCode(),equalTo(SC_UNAUTHORIZED));

        assertThat(responsePlaylist.getError().getStatus(), equalTo(SC_UNAUTHORIZED));
        assertThat(responsePlaylist.getError().getMessage(), equalTo("Invalid access token"));*/
    }



}
