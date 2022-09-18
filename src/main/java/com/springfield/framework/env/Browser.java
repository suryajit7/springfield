package com.springfield.framework.env;

public enum Browser {

    CHROME("chrome"), FIREFOX("firefox"), EDGE("edge"),
    INTERNET_EXPLORER("internet explorer"), GHOST("ghost");

    private final String strValue;

    public String getStrValue() {
        return this.strValue;
    }

    Browser(String filetype) {
        this.strValue = filetype;
    }
}
