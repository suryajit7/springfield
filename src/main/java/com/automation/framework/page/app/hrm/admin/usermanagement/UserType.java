package com.automation.framework.page.app.hrm.admin.usermanagement;

public enum UserType {

    ALL("All"), ADMIN("Admin"), ESS("ESS");

    private final String stringValue;

    UserType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }
}
