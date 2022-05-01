package com.automation.framework.unit;

import com.automation.framework.SpringfieldApplicationTests;
import com.automation.framework.util.testhelper.AssertField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CustomAssertionsTest extends SpringfieldApplicationTests {

    @Test
    @Order(1)
    public void verifyGivenFieldIsValidPhoneNumber(){

        AssertField.assertThat("+1".concat(faker.phoneNumber().cellPhone()))
                .isPhoneNumber()
                .isPhoneNumberFrom("IN");

        AssertField.assertThat("+91".concat(faker.phoneNumber().phoneNumber()))
                .isPhoneNumber()
                .isPhoneNumberFrom("US");

        AssertField.assertThat("+911234567890")
                .isPhoneNumber()
                .isPhoneNumberFrom("IN");
    }

    @Test
    @Order(2)
    public void verifyGivenFieldIsValidEmailID(){

        AssertField.assertThat(faker.internet().emailAddress())
                .isEmailID();

        AssertField.assertThat(faker.internet().emailAddress())
                .isEmailID();

        AssertField.assertThat(faker.internet().emailAddress().toUpperCase())
                .isEmailID();

    }

    @Test
    @Order(3)
    public void verifyGivenFieldIsValidURL(){

        AssertField.assertThat(faker.internet().url())
                .isURL();

        AssertField.assertThat(faker.internet().url())
                .isURL();

        AssertField.assertThat(faker.internet().url())
                .isURL();
    }

    @Test
    @Order(4)
    public void verifyGivenFieldIsValidISOCountryCode(){

        AssertField.assertThat(faker.country().countryCode2().toUpperCase())
                .isCountryCodeISO();

        AssertField.assertThat(faker.country().countryCode2().toUpperCase())
                .isCountryCodeISO();
    }









}
