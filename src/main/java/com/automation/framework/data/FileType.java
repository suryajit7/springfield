package com.automation.framework.data;

public enum FileType {

    TXT("text"), XML("xml"), JSON("json"), PDF("pdf"), XLS("xls"), XLSX("xlsx"),
    CSV("csv"), PNG("png"), JPEG("jpeg"), JPG("jpg"), GIF("gif");

    private final String stringValue;

    public String getStringValue() {
        return this.stringValue;
    }

    FileType(String filetype) {
        this.stringValue = filetype;
    }
}
