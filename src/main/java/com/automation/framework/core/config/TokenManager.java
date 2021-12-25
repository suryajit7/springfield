package com.automation.framework.core.config;

import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.service.BaseService;
import com.automation.framework.service.SpecBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;

import static com.automation.framework.data.Constants.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.URLENC;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@Component
public class TokenManager extends BaseService {

    private static String accessToken;
    private static Instant expiryTime;


    @LazyAutowired
    private SpecBuilder specBuilder;

    @Value("${app.spotify.accounts.url}")
    private String spotifyAccountsUrl;

    public String generateAccessToken(){
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)){
                accessToken = getRenewToken().path(ACCESS_TOKEN);
                int expiryDuration = getRenewToken().path(EXPIRY_TIME);
                expiryTime = Instant.now().plusSeconds(expiryDuration - 300);
            } else {
                log.info("Token is still Active");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return BEARER.concat(SPACE).concat(accessToken);
    }

    private Response getRenewToken(){

        Response response = given()
                .baseUri(spotifyAccountsUrl)
                .contentType(URLENC)
                .formParams(getFormParams())
                .log().all()
                .when().post("/api/token")
                .then().spec(specBuilder.getResponseSpec())
                .extract().response();

        assertThat(response.statusCode())
                .isNotNull()
                .isEqualTo(SC_OK)
                .withFailMessage("Renew token failed!!!")
                .descriptionText();

        return response;
    }


    private HashMap<String, String> getFormParams() {

        HashMap<String, String> formParams = new HashMap<String, String>();

        formParams.put(CLIENT_ID, SPOTIFY_CLIENT_ID);
        formParams.put(CLIENT_SECRET, decryptService.getSpotifyClientSecret());
        formParams.put(REFRESH_TOKEN, decryptService.getSpotifyRefreshToken());
        formParams.put(GRANT_TYPE, REFRESH_TOKEN);

        return formParams;
    }
}
