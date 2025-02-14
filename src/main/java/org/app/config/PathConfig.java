package org.app.config;

import java.nio.file.Path;

public enum PathConfig {
    DATA_INPUT_PATH("src/main/java/org/app/sampleData/stock_data.txt"),
    DATA_VALID_OUTPUT_PATH("src/main/java/org/app/output/result_data.txt"),
    DATA_NOT_VALID_OUTPUT_PATH("src/main/java/org/app/output/invalid_data.txt");

    private String stringValue;
    private Path pathValue;

    private PathConfig(String stringValue) {
        this.stringValue = stringValue;
        this.pathValue = Path.of(stringValue);
    }

    private PathConfig(Path pathValue) {
        this.pathValue = pathValue;
        this.stringValue = pathValue.toString();
    }

    public String getStringValue() {
        return stringValue;
    }

    public Path getPathValue() {
        return pathValue;
    }
}
