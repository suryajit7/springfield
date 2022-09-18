package com.springfield.framework.functional.api;

import com.springfield.framework.SpringfieldApplicationTests;
import com.springfield.framework.core.annotation.LazyAutowired;
import com.springfield.framework.data.entity.Playlist;
import com.springfield.framework.service.spotify.api.PlaylistService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpotifyPlaylistTest extends SpringfieldApplicationTests {

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

        Assertions.assertThat(playlistIds)
                .isNotNull();
    }



}
