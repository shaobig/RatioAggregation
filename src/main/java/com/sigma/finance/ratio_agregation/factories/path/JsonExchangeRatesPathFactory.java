package com.sigma.finance.ratio_agregation.factories.path;

public class JsonExchangeRatesPathFactory implements ExchangeRatesPathFactory {

    private static final String ROOT_PATH = "src\\main\\resources\\static";

    @Override
    public String getExchangeRatesPath(String bankName) {
        if (bankName == null) {
            throw new NullPointerException("The bank name has no reference to an object");
        }
        if (bankName.isEmpty()) {
            throw new IllegalArgumentException("The proposed name is empty");
        }

        return ROOT_PATH.concat("\\").concat(bankName.toLowerCase()).concat(".json");
    }
}
