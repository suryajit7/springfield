package com.automation.framework.util;

import com.automation.framework.core.annotation.LazyService;
import org.springframework.beans.factory.annotation.Value;

@LazyService
public class PropertyDecryptService {

    @Value("${app.postman.key}")
    private String postmanKey;

    @Value("${app.spotify.client-secret}")
    private String spotifyClientSecret;

    @Value("${app.spotify.key}")
    private String spotifyAccessToken;

    @Value("${app.spotify.refresh-token}")
    private String spotifyRefreshToken;

    @Value("${app.spotify.expired-key}")
    private String expiredSpotifyAccessToken;

    @Value("${app.saucedemo.password}")
    private String saucedemoPassword;

    public String getPostmanKey() {
        return postmanKey;
    }

    public String getSpotifyClientSecret() {
        return spotifyClientSecret;
    }

    public String getSpotifyRefreshToken() {
        return spotifyRefreshToken;
    }

    public String getExpiredSpotifyAccessToken() {
        return expiredSpotifyAccessToken;
    }

    public String getSauceDemoPassword() {
        return saucedemoPassword;
    }


}
