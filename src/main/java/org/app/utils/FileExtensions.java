package org.app.utils;

public enum FileExtensions {
    TXT(".txt"),
    CSV(".csv"),
    LOG(".log"),
    XML(".xml");

    private String value;

    private FileExtensions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
