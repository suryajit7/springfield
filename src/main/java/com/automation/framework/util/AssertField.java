package com.automation.framework.util;

import com.google.i18n.phonenumbers.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.assertj.core.api.AbstractAssert;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.*;

@Slf4j
public class AssertField extends AbstractAssert<AssertField, String> {

    public AssertField(String string) {
        super(string, AssertField.class);
    }

    public static AssertField assertThat(String string) {
        return new AssertField(string);
    }


    public AssertField isPhoneNumber() throws NumberParseException {
        isNotNull();

        Boolean isValidPhoneNumber = PhoneNumberUtil.getInstance()
                .isValidNumber(PhoneNumberUtil.getInstance()
                        .parse(actual, CountryCodeSource.UNSPECIFIED.name()));

        if (isValidPhoneNumber) {
            log.info("Given string is a valid phone number in Earth.");
        } else {
            log.error("Given string is NOT a valid phone number in Earth.");
            failWithMessage("Given string is NOT a valid phone number in Earth.");
        }
        return this;
    }

    public AssertField isNumberFrom(String geographicalCode) throws NumberParseException {
        isNotNull();

        Boolean isNumberFromGivenGeographical = PhoneNumberUtil.getInstance()
                .isNumberGeographical(PhoneNumberUtil.getInstance()
                        .parse(actual, geographicalCode));

        if (isNumberFromGivenGeographical) {
            log.info("The PhoneNumber belongs to given geographical area.");
        } else {
            log.error("The PhoneNumber DOES NOT belongs to given geographical area.");
            failWithMessage("The PhoneNumber DOES NOT belongs to given geographical area.");
        }
        return this;
    }


    public AssertField isEmailID() {
        isNotNull();

        if (EmailValidator.getInstance().isValid(actual)) {
            log.info("Given EmailID is valid.");
        } else {
            log.error("Given EmailID is NOT valid.");
            failWithMessage("Given EmailID is NOT valid.");
        }
        return this;
    }
}
