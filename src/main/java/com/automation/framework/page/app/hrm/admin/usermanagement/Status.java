package com.automation.framework.page.app.hrm.admin.usermanagement;

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
