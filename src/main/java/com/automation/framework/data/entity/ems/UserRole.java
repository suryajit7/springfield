package com.automation.framework.data.entity.ems;

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
