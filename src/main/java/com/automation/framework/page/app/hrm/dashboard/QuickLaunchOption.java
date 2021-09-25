package com.automation.framework.page.app.hrm.dashboard;

public enum QuickLaunchOption {

    ASSIGN_LEAVE("Assign Leave"), LEAVE_LIST("Leave List"), TIMESHEETS("Timesheets"),
    APPLY_LEAVE("Apply Leave"), MY_LEAVE("My Leave"), MY_TIMESHEET("My Timesheet");

    private final String stringValue;

    public String getStringValue() {
        return this.stringValue;
    }

    QuickLaunchOption(String option) {
        this.stringValue = option;
    }

}
