package com.automation.framework.unit;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.service.RestResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.automation.framework.data.Constants.BEARER;

public class PropertyDecryptTest extends AutomationSuiteApplicationTests {

    @Autowired
    public RestResource resource;

    @Test
    @Order(1)
    public void verifyGivenFieldIsValidPhoneNumber(){

        Assertions.assertThat(resource.decryptService.getSpotifyAccessToken(false))
                .isNotBlank()
                .startsWith(BEARER)
                .contains(BEARER);

    }
}
