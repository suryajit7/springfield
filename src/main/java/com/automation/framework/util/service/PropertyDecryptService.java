package com.automation.framework.util.service;

import com.automation.framework.core.annotation.LazyService;
import org.springframework.beans.factory.annotation.Value;

@LazyService
public class PropertyDecryptService {

    @Value("${app.spotify.key}")
    private String spotifyAccessToken;

    @Value("${app.spotify.expired-key}")
    private String spotifyExpiredAccessToken;

    public String getSpotifyAccessToken(Boolean expiredToken) {
        return expiredToken ? spotifyExpiredAccessToken : spotifyAccessToken;
    }


}
