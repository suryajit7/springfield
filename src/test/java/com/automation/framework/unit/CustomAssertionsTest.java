package com.automation.framework.unit;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.core.bean.FakerConfig;
import com.automation.framework.util.AssertField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomAssertionsTest extends AutomationSuiteApplicationTests {

    @Autowired
    private FakerConfig faker;

    @Test
    @Order(1)
    public void verifyGivenFieldIsValidPhoneNumber(){

        AssertField.assertThat("+1".concat(faker.getFakerConfig().phoneNumber().cellPhone()))
                .isPhoneNumber()
                .isPhoneNumberFrom("IN");

        AssertField.assertThat("+91".concat(faker.getFakerConfig().phoneNumber().phoneNumber()))
                .isPhoneNumber()
                .isPhoneNumberFrom("US");

        AssertField.assertThat("+911234567890")
                .isPhoneNumber()
                .isPhoneNumberFrom("IN");
    }

    @Test
    @Order(2)
    public void verifyGivenFieldIsValidEmailID(){

        AssertField.assertThat(faker.getFakerConfig().internet().emailAddress())
                .isEmailID();

        AssertField.assertThat(faker.getFakerConfig().internet().emailAddress())
                .isEmailID();

        AssertField.assertThat(faker.getFakerConfig().internet().emailAddress().toUpperCase())
                .isEmailID();

    }

    @Test
    @Order(3)
    public void verifyGivenFieldIsValidURL(){

        AssertField.assertThat(faker.getFakerConfig().internet().url())
                .isURL();

        AssertField.assertThat(faker.getFakerConfig().internet().url())
                .isURL();

        AssertField.assertThat(faker.getFakerConfig().internet().url())
                .isURL();
    }

    @Test
    @Order(4)
    public void verifyGivenFieldIsValidISOCountryCode(){

        AssertField.assertThat(faker.getFakerConfig().country().countryCode2().toUpperCase())
                .isCountryCodeISO();

        AssertField.assertThat(faker.getFakerConfig().country().countryCode2().toUpperCase())
                .isCountryCodeISO();
    }









}
