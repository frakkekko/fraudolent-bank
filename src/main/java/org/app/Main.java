package org.app;

import org.app.utils.DocumentParser;
import org.app.utils.FileProvider;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<String> data = new ArrayList<>();
        Path path = Path.of("src/main/java/org/app/sampleData/stock_data.txt");

        try {
            data = FileProvider.readLinesOfTxtFile(path);

        } catch (Exception error) {
            System.out.println(error);
        }

        System.out.println(data);

        DocumentParser.parse(data);

    }
}