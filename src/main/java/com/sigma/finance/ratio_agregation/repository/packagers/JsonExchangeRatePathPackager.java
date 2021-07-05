package com.sigma.finance.ratio_agregation.repository.packagers;

public class JsonExchangeRatePathPackager implements ExchangeRatePathPackager {

    private static final String ROOT_PATH = "src\\main\\resources\\static";

    @Override
    public String getExchangeRatePath(String bankName) {
        if (bankName == null) {
            throw new NullPointerException("The bank name has no reference to an object");
        }
        if (bankName.isEmpty()) {
            throw new IllegalArgumentException("The proposed name is empty");
        }

        return ROOT_PATH.concat("\\").concat(bankName.toLowerCase()).concat(getFileFormat());
    }

    @Override
    public String getFileFormat() {
        return ".json";
    }
}
