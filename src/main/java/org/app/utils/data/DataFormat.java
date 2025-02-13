package org.app.utils.data;

// "^[^\\s]+ (\\d+(\\.\\d+)?|\\d+)( [1-9]\\d*) [SB]$"

public enum DataFormat {
    ENTRY(String.format(
            "^[^\\s]+ (\\d+(\\.\\d+)?|\\d+)( [1-9]\\d*) [%s%s]$",
            DataConfig.SELL_SYMBOL.getValue(),
            DataConfig.BUY_SYMBOL.getValue()));

    private String regex;

    private DataFormat(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
