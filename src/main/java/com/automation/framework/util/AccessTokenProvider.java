package com.automation.framework.util;

import com.automation.framework.core.ConfigurableBean;
import com.automation.framework.core.annotation.LazyService;
import io.restassured.response.Response;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import static com.automation.framework.data.Constants.ACCESS_TOKEN;
import static com.automation.framework.data.Constants.EXPIRY_TIME;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@LazyService
public class AccessTokenProvider {

    private static AppContextProvider appCtx = new AppContextProvider();

    private static String accessToken;
    private static Instant expiryTime;

    @Synchronized
    public static String getAccessToken(){

        PropertyDecryptService decryptService = appCtx.getBeanOfType(PropertyDecryptService.class);
        ConfigurableBean myBean = appCtx.getBeanOfType(ConfigurableBean.class);

        return myBean.getExpiredAccessToken() ? decryptService.getExpiredSpotifyAccessToken() : generateAccessToken();
    }

    public static String generateAccessToken(){
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)){
                Response response = postAccount();
                accessToken = response.path(ACCESS_TOKEN);
                int expiryDuration = response.path(EXPIRY_TIME);
                expiryTime = Instant.now().plusSeconds(expiryDuration - 300);
            } else {
                log.info("Token is still Active.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } return accessToken;
    }

    public static Response postAccount(){
        SpecBuilder specBuilder = appCtx.getBeanOfType(SpecBuilder.class);

        Response response = given(specBuilder.getAccountSpec())
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

}
