package com.automation.framework.service.api.spotify;

import com.automation.framework.core.annotation.LazyService;
import com.automation.framework.data.entity.app.spotify.Playlist;
import com.automation.framework.service.BaseService;
import io.restassured.response.Response;


@LazyService
public class PlaylistService extends BaseService {


    public Response getPlaylist(String id){
        return get("/playlists/{resource_ID}", id);
    }


    public Response getUserId(String id) {
        return get("/users/{resource_ID}/playlists", id);
    }


    public Response post(String id, Playlist requestPlaylist, Boolean expiredToken){
        return post("/users/{resource_ID}/playlists", id, requestPlaylist, expiredToken);
    }


    public Response update(String id, Playlist requestPlaylist){
        return update("/playlists/{resource_ID}", id, requestPlaylist);
    }



}
