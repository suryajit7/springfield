package com.springfield.framework.data;

public enum FileType {

    TXT("txt"), CSV("csv"), XML("xml"), JSON("json"), XLS("xls"), XLSX("xlsx"),
    PDF("pdf"), PNG("png"), JPEG("jpeg"), JPG("jpg"), GIF("gif");

    private final String stringValue;

    public String getStringValue() {
        return this.stringValue;
    }

    FileType(String filetype) {
        this.stringValue = filetype;
    }
}
