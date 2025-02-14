package org.app.utils.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {
    public static ParsedData parse(List<String> data) {

        List<Map<String, String>> validDataParsed = data.stream().filter(DataParser::isValidEntry).map(str -> {
            Map<String, String> extractedValues = new HashMap<>();
            String[] splittedStr = str.split(" ");

            extractedValues.put("action", splittedStr[0]);
            extractedValues.put("price", splittedStr[1]);
            extractedValues.put("quantity", splittedStr[2]);
            extractedValues.put("operation", splittedStr[3]);

            return extractedValues;
        }).toList();

        List<String> invalidDataParsed = data.stream().filter(entry -> !DataParser.isValidEntry(entry)).toList();

        return new ParsedData(validDataParsed, invalidDataParsed);
    }

    private static boolean isValidEntry(String entry) {
        return entry.matches(DataFormat.ENTRY.getRegex());
    }


}
