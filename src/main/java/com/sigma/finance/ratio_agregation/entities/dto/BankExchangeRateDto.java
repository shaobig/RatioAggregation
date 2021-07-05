package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;

import java.util.Objects;

public class BankExchangeRateDto {

    private BankName bankName;
    private ExchangeRate exchangeRate;

    public BankExchangeRateDto(String bankName, ExchangeRate exchangeRate) {
        this.bankName = BankName.valueOf(bankName.toUpperCase());
        this.exchangeRate = exchangeRate;
    }

    public BankExchangeRateDto(BankName bankName, ExchangeRate exchangeRate) {
        this.bankName = bankName;
        this.exchangeRate = exchangeRate;
    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        if (bankName == null) {
            throw new NullPointerException("The bank name has no reference to an object");
        }

        this.bankName = bankName;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        if (exchangeRate == null) {
            throw new NullPointerException("The exchange rate for the bank has no reference to an object");
        }

        this.exchangeRate = exchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankExchangeRateDto that = (BankExchangeRateDto) o;
        return bankName == that.bankName &&
                exchangeRate.equals(that.exchangeRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, exchangeRate);
    }

    @Override
    public String toString() {
        return "ExchangeRateDto{" +
                "bankName=" + bankName +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
