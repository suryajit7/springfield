package com.automation.framework.page.app.hrm.admin.usermanagement;

public enum UserRole {

    ADMIN("Admin"), ESS("ESS");

    private final String stringValue;

    UserRole(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
