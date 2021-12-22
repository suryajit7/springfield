package com.automation.framework.util.service;

import com.automation.framework.core.annotation.LazyService;
import org.springframework.beans.factory.annotation.Value;

@LazyService
public class PropertyDecryptService {

    @Value("${app.spotify.key}")
    private String spotifyAccessToken;

    public String getSpotifyAccessToken() {
        return spotifyAccessToken;
    }


}
