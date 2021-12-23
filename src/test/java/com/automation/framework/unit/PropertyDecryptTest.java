package com.automation.framework.unit;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.core.annotation.LazyAutowired;
import com.automation.framework.service.SpecBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static com.automation.framework.data.Constants.BEARER;

public class PropertyDecryptTest extends AutomationSuiteApplicationTests {

    @LazyAutowired
    public SpecBuilder specBuilder;
    @Test
    @Order(1)
    public void verifyGivenFieldIsValidPhoneNumber(){

        Assertions.assertThat(specBuilder.decryptService.getSpotifyAccessToken(false))
                .isNotBlank()
                .startsWith(BEARER)
                .contains(BEARER);

    }
}
