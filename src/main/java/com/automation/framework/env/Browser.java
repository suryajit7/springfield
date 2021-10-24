package com.automation.framework.env;

public enum Browser {

    CHROME("chrome"), FIREFOX("firefox"), EDGE("edge"),
    INTERNET_EXPLORER("ie"), GHOST("ghost");

    private final String stringValue;

    public String getStringValue() {
        return this.stringValue;
    }

    Browser(String filetype) {
        this.stringValue = filetype;
    }
}
