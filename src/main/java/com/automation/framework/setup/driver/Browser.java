package com.automation.framework.setup.driver;

public enum Browser {

    CHROME("chrome"), FIREFOX("firefox"), EDGE("edge"),
    INTERNET_EXPLORER("internet explorer"), GHOST("ghost");

    private final String stringValue;

    public String getStringValue() {
        return this.stringValue;
    }

    Browser(String filetype) {
        this.stringValue = filetype;
    }
}