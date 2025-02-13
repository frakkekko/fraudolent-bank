package org.app.utils.data;

public enum DataConfig {
    BUY_SYMBOL("B"),
    SELL_SYMBOL("S");

    private String value;

    private DataConfig(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
