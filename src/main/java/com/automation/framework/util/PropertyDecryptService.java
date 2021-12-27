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
    private String spotifyExpiredAccessToken;

    @Value("${app.hrm.password}")
    private String orangeHrmPassword;

    public String getPostmanKey() {
        return postmanKey;
    }

    public String getSpotifyClientSecret() {
        return spotifyClientSecret;
    }

    public String getSpotifyRefreshToken() {
        return spotifyRefreshToken;
    }

    public String getSpotifyAccessToken(Boolean expiredToken) {
        return expiredToken ? spotifyExpiredAccessToken : spotifyAccessToken;
    }

    public String getOrangeHrmPassword() {
        return orangeHrmPassword;
    }


}
