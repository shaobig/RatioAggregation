package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;

public class ExchangeRateDto {
    private BankName bankName;
    private ExchangeRate exchangeRate;

    public ExchangeRateDto(String bankName, ExchangeRate exchangeRate) {
        this.bankName = BankName.valueOf(bankName.toUpperCase());
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRateDto(BankName bankName, ExchangeRate exchangeRate) {
        this.bankName = bankName;
        this.exchangeRate = exchangeRate;
    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        this.bankName = bankName;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
