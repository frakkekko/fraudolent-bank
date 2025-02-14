package org.app.utils.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParsedData {
    private final List<Map<String, String>> validDataParsed;
    private final List<String> invalidData;

    private Double cachedSellSum;
    private Double cachedBuySum;

    // package-private
    ParsedData(List<Map<String, String>> validDataParsed, List<String> invalidData){
        this.validDataParsed = validDataParsed;
        this.invalidData = invalidData;
    }

    private Double calcSumOperation(String operationSymbol) {
        return validDataParsed.stream()
                .filter(dataInfo -> dataInfo.get("operation").equals(operationSymbol))
                .map(data -> Double.valueOf(data.get("price")))
                .reduce(0.0, Double::sum);
    }

    public List<Map<String, String>> getValidDataParsed() {
        return validDataParsed;
    }

    public List<String> getValidDataParsedString() {
        return validDataParsed.stream().map(entry -> String.format("-- NAME: %s -- OPERATION: %s -- PRICE: %s -- QUANTITY: %s", entry.get("action"), entry.get("operation"), entry.get("price"), entry.get("quantity")))
                .toList();
    }

    public List<String> getInvalidData() {
        return invalidData;
    }

    public Integer getNumberValidOperations() {
        return validDataParsed.size();
    }

    public Integer getNumberNotValidOperations() {
        return invalidData.size();
    }

    public Double getTotalSell() {
        if(cachedSellSum == null) {
            cachedSellSum = calcSumOperation(DataConfig.SELL_SYMBOL.getValue());
        }

        return cachedSellSum;
    }

    public Double getTotalBuy() {
        if(cachedBuySum == null) {
            cachedBuySum = calcSumOperation(DataConfig.BUY_SYMBOL.getValue());
        }

        return cachedBuySum;
    }

    public Integer getTotalOperations() {
        return (validDataParsed.size() + invalidData.size());
    }

    public Integer getTotalBuyOperation() {
        return validDataParsed.stream()
                .filter(dataInfo -> dataInfo.get("operation").equals(DataConfig.BUY_SYMBOL.getValue())).toList().size();
    }

    public Integer getTotalSellOperation() {
        return validDataParsed.stream()
                .filter(dataInfo -> dataInfo.get("operation").equals(DataConfig.SELL_SYMBOL.getValue())).toList().size();
    }

    public List<String> presentValidData() {
        List<String> validData = new ArrayList<>(getValidDataParsedString());
        validData.add(0, "DATA RESULT:");
        validData.add(String.format("\nOPERATIONS RECAP:\n\t- TOTAL OPERATIONS: %d (BUY %d SELL %d)\n\t- BUY: %f\n\t- SELL: %f", getNumberValidOperations(), getTotalBuyOperation(), getTotalSellOperation(), getTotalBuy(), getTotalSell()));

        return validData;
    }

    public List<String> presentInvalidData(){
        List<String> invalidData = new ArrayList<>(getInvalidData()).stream().map(entry -> String.format("\t- %s", entry)).collect(Collectors.toList());
        invalidData.add(0, String.format("DATA NOT VALID (%d record/s not valid):\n", getNumberNotValidOperations()));

        return invalidData;
    }
}
