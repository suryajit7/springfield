package com.automation.framework.unit;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.core.AssertField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CustomAssertionsTest extends AutomationSuiteApplicationTests {

    @Test
    @Order(1)
    public void verifyGivenFieldIsValidPhoneNumber(){

        AssertField.assertThat("+1".concat(faker.phoneNumber().cellPhone()))
                .isPhoneNumber()
                .isPhoneNumberFrom("IN");

        AssertField.assertThat("+91".concat(fakerConfig.getFakerConfig().phoneNumber().phoneNumber()))
                .isPhoneNumber()
                .isPhoneNumberFrom("US");

        AssertField.assertThat("+911234567890")
                .isPhoneNumber()
                .isPhoneNumberFrom("IN");
    }

    @Test
    @Order(2)
    public void verifyGivenFieldIsValidEmailID(){

        AssertField.assertThat(fakerConfig.getFakerConfig().internet().emailAddress())
                .isEmailID();

        AssertField.assertThat(fakerConfig.getFakerConfig().internet().emailAddress())
                .isEmailID();

        AssertField.assertThat(fakerConfig.getFakerConfig().internet().emailAddress().toUpperCase())
                .isEmailID();

    }

    @Test
    @Order(3)
    public void verifyGivenFieldIsValidURL(){

        AssertField.assertThat(fakerConfig.getFakerConfig().internet().url())
                .isURL();

        AssertField.assertThat(fakerConfig.getFakerConfig().internet().url())
                .isURL();

        AssertField.assertThat(fakerConfig.getFakerConfig().internet().url())
                .isURL();
    }

    @Test
    @Order(4)
    public void verifyGivenFieldIsValidISOCountryCode(){

        AssertField.assertThat(fakerConfig.getFakerConfig().country().countryCode2().toUpperCase())
                .isCountryCodeISO();

        AssertField.assertThat(fakerConfig.getFakerConfig().country().countryCode2().toUpperCase())
                .isCountryCodeISO();
    }









}
