package org.app.utils.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParsedData {
    private List<Map<String, String>> validDataParsed;
    private List<String> invalidData;

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

    public Integer getNumberValidEntries() {
        return validDataParsed.size();
    }

    public Integer getNumberNotValidEntries() {
        return invalidData.size();
    }

    public Double getTotalSell() {
        if(cachedSellSum != null) return cachedSellSum;

        return calcSumOperation(DataConfig.SELL_SYMBOL.getValue());
    }

    public Double getTotalBuy() {
        if(cachedBuySum != null) return cachedBuySum;

        return calcSumOperation(DataConfig.BUY_SYMBOL.getValue());
    }

    public Integer getTotalOperations() {
        return (validDataParsed.size() + invalidData.size());
    }

    @Override
    public String toString() {
        return "ParsedData{" +
                ", cachedSellSum=" + cachedSellSum +
                ", cachedBuySum=" + cachedBuySum +
                '}';
    }
}
