package org.app;

import org.app.utils.data.DataParser;
import org.app.utils.data.ParsedData;
import org.app.utils.file.FileService;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class App {
    public void start() {
        List<String> data;
        Path path = Path.of("src/main/java/org/app/sampleData/stock_data.txt");

        try {
            data = FileService.readLinesOfTxtFile(path);
            ParsedData parsedData = DataParser.parse(data);

            FileService.writeTxtOutputFile(Path.of("src/main/java/org/app/output/result_data.txt"), parsedData.getValidDataParsedString());
            FileService.writeTxtOutputFile(Path.of("src/main/java/org/app/output/invalid_data.txt"), parsedData.getInvalidData());

        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
