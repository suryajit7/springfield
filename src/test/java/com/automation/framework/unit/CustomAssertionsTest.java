package com.automation.framework.unit;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.util.AssertField;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CustomAssertionsTest extends AutomationSuiteApplicationTests {


    @Test
    @Order(1)
    public void verifySimpleAssertFieldTest(){

        AssertField.assertThat("sas").isCountryCode();


    }









}
