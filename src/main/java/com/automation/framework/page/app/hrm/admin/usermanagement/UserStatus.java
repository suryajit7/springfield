package com.automation.framework.page.app.hrm.admin.usermanagement;

public enum UserStatus {

    ALL("All"), ENABLED("Enabled"), DISABLED("Disabled");

    private final String stringValue;

    UserStatus(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
