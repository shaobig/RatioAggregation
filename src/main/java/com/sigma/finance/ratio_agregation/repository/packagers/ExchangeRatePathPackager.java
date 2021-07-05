package com.sigma.finance.ratio_agregation.repository.packagers;

public interface ExchangeRatePathPackager {
    String getExchangeRatePath(String bankName);
    String getFileFormat();
}
