package org.app;

import org.app.config.PathConfig;
import org.app.utils.data.DataParser;
import org.app.utils.data.ParsedData;
import org.app.utils.file.FileService;

import java.util.List;

public class App {
    public void start() {

        try {
            List<String> data = FileService.readLinesOfTxtFile(PathConfig.DATA_INPUT_PATH.getPathValue());
            ParsedData parsedData = DataParser.parse(data);

            FileService.writeTxtOutputFile(PathConfig.DATA_VALID_OUTPUT_PATH.getPathValue(), parsedData.presentValidData());
            FileService.writeTxtOutputFile(PathConfig.DATA_NOT_VALID_OUTPUT_PATH.getPathValue(), parsedData.presentInvalidData());

            System.out.println("ANALYSES COMPLETED!");
            System.out.println(String.format("OUTPUT FILES GENERATED:\n- %s\n- %s", PathConfig.DATA_VALID_OUTPUT_PATH.getStringValue(), PathConfig.DATA_NOT_VALID_OUTPUT_PATH.getStringValue()));

        } catch (Exception error) {
            System.out.println("SOMETHING WENT WRONG IN:");
            error.printStackTrace();
        }
    }
}
