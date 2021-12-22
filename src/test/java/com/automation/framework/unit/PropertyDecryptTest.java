package com.automation.framework.unit;

import com.automation.framework.AutomationSuiteApplicationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static com.automation.framework.data.Constants.BEARER;

public class PropertyDecryptTest extends AutomationSuiteApplicationTests {

    @Test
    @Order(1)
    public void verifyGivenFieldIsValidPhoneNumber(){

        Assertions.assertThat(decryptService.getSpotifyAccessToken())
                .isNotBlank()
                .startsWith(BEARER)
                .contains(BEARER);

        logger.info(decryptService.getSpotifyAccessToken());

    }
}
