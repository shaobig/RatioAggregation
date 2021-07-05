package com.sigma.finance.ratio_agregation.repository.packagers;

public interface ExchangeRatesPathPackager {
    String getExchangeRatesPath(String bankName);
    String getFileFormat();
}
