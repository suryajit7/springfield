package com.automation.framework.util;

import com.automation.framework.core.annotation.LazyService;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import static com.automation.framework.data.Constants.*;

@Slf4j
@LazyService
public class TokenService {

    private static String accessToken;
    private static Instant expiryTime;

    private static PropertyDecryptService decryptService;

    public static String generateAccessToken(Response response, Boolean expiredToken){
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)){
                accessToken = response.path(ACCESS_TOKEN);
                int expiryDuration = response.path(EXPIRY_TIME);
                expiryTime = Instant.now().plusSeconds(expiryDuration - 300);
            } else if (expiredToken) {
                accessToken = decryptService.getSpotifyAccessToken(expiredToken);
            } else {
                log.info("Token is still Active.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return BEARER.concat(SPACE).concat(accessToken);
    }
}
