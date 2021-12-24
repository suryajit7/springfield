package com.automation.framework.service.api;

import com.automation.framework.data.entity.app.spotify.Playlist;
import com.automation.framework.service.RestResource;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PlaylistService extends RestResource {


    public Response getPlaylist(String id){
        return get("/playlists/{_id}", id);
    }

    public Response getUserId(String id) {
        return get("/users/{_id}/playlists", id);
    }

    public Response post(String id, Playlist requestPlaylist, Boolean expiredToken){
        return post("/users/{_id}/playlists", id, requestPlaylist, expiredToken);
    }

    public Response update(String id, Playlist requestPlaylist){
        return update("/playlists/{_id}", id, requestPlaylist);
    }



}
