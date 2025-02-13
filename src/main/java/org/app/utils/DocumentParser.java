package org.app.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentParser {
    public static void parse(List<String> data) {
        data.forEach(entry -> System.out.println(String.format("Entry: '%s' -- isValid: %s", entry, DocumentParser.isValidEntry(entry))));

        Map[] parsedData = data.stream().filter(DocumentParser::isValidEntry).map(str -> {
            Map<String, String> extractedValues = new HashMap<>();
            String[] splittedStr = str.split(" ");

            String actionName = splittedStr[0];
            String price = splittedStr[1];
            String quantity = splittedStr[2];
            String operation = splittedStr[3];

            extractedValues.put("action", actionName);
            extractedValues.put("price", price);
            extractedValues.put("quantity", quantity);
            extractedValues.put("operation", operation);

            return extractedValues;
        }).toArray(HashMap[]::new);

        Arrays.stream(parsedData).forEach(element -> System.out.println(element));

    }

    private static boolean isValidEntry(String entry) {
        return entry.matches(DocFormat.ENTRY.getRegex());
    }


}
