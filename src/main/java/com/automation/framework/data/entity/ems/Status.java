package com.automation.framework.data.entity.ems;

public enum Status {

    ENABLED("Enabled"), DISABLED("Disabled");

    private final String stringValue;

    Status(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
