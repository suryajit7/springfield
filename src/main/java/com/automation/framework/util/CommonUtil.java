package com.automation.framework.util;

public class CommonUtil {

    public String getDynamicLocator(String locator, String newString) {
        return String.format(locator, newString);
    }
}
