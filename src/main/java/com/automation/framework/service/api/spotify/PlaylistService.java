package com.automation.framework.service.api.spotify;

import com.automation.framework.core.annotation.LazyService;
import com.automation.framework.data.entity.spotify.Playlist;
import com.automation.framework.service.BaseService;
import io.restassured.response.Response;


@LazyService
public class PlaylistService extends BaseService {

    private static final String PLAYLISTS_PATH_PARAM = "/playlists/{path_param}";
    private static final String USERS_PATH_PARAM_PLAYLISTS = "/users/{path_param}/playlists";

    public Response getPlaylist(String id){
        return get(PLAYLISTS_PATH_PARAM, id);
    }


    public Response getPlaylistIDsForGivenUserID(String id) {
        return get(USERS_PATH_PARAM_PLAYLISTS, id);
    }


    public Response post(String id, Playlist requestPlaylist){
        return post(USERS_PATH_PARAM_PLAYLISTS, id, requestPlaylist);
    }


    public Response update(String id, Playlist requestPlaylist){
        return update(PLAYLISTS_PATH_PARAM, id, requestPlaylist);
    }



}
