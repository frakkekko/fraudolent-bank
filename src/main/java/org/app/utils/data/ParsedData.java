package org.app.utils.data;

import java.util.Arrays;
import java.util.Map;

public class ParsedData {
    private Map<String, String>[] validDataParsed;
    private String[] invalidData;

    private Double cachedSellSum;
    private Double cachedBuySum;

    // package-private
    ParsedData(Map<String, String>[] validDataParsed, String[] invalidData){
        this.validDataParsed = validDataParsed.clone();
        this.invalidData = invalidData.clone();
    }

    private Double calcSumOperation(String operationSymbol) {
        return Arrays.stream(validDataParsed)
                .filter(dataInfo -> dataInfo.get("operation").equals(operationSymbol))
                .map(data -> Double.valueOf(data.get("price")))
                .reduce(0.0, Double::sum);
    }

    public Map<String, String>[] getValidDataParsed() {
        return validDataParsed;
    }

    public String[] getInvalidData() {
        return invalidData;
    }

    public Integer getNumberValidEntries() {
        return validDataParsed.length;
    }

    public Integer getNumberNotValidEntries() {
        return invalidData.length;
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
        return (validDataParsed.length + invalidData.length);
    }

    @Override
    public String toString() {
        return "ParsedData{" +
                ", cachedSellSum=" + cachedSellSum +
                ", cachedBuySum=" + cachedBuySum +
                '}';
    }
}
