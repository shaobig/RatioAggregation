package com.sigma.finance.ratio_agregation.entities;

public class Bank {
    private BankName name;
    private ExchangeRate rate;

    public BankName getName() {
        return name;
    }

    public void setName(BankName name) {
        this.name = name;
    }

    public ExchangeRate getRate() {
        return rate;
    }

    public void setRate(ExchangeRate rate) {
        this.rate = rate;
    }
}
