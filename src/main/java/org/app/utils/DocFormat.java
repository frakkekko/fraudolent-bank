package org.app.utils;

public enum DocFormat {
    ENTRY("^[^\\s]+ (\\d+(\\.\\d+)?|\\d+)( [1-9]\\d*) [SB]$");

    private String regex;

    private DocFormat(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
