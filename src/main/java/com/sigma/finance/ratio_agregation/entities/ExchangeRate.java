package com.sigma.finance.ratio_agregation.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeRate {

    private String code;
    private BigDecimal buy;
    private BigDecimal sell;

    public ExchangeRate() {}

    public ExchangeRate(String code, BigDecimal buy, BigDecimal sell) {
        this.code = code;
        this.buy = buy;
        this.sell = sell;
    }

    public ExchangeRate(String code, String buy, String sell) {
        this.code = code;
        this.buy = new BigDecimal(buy);
        this.sell = new BigDecimal(sell);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null) {
            throw new NullPointerException("The currency code has no reference to an object");
        }
        if (code.isEmpty()) {
            throw new IllegalArgumentException("The code value is empty");
        }

        if (code.matches(".*\\d.*")) {
            throw new IllegalArgumentException("The currency code value contains at least one digital symbol");
        }

        this.code = code;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        if (buy == null) {
            throw new NullPointerException("The buy price value has no reference to an object");
        }
        if (buy.intValue() < 0) {
            throw new IllegalArgumentException("The buy price value should be a positive number");
        }

        this.buy = buy;
    }

    public void setBuy(String buy) {
        setBuy(new BigDecimal(buy));
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        if (sell == null) {
            throw new NullPointerException("The sell price value has no reference to an object");
        }
        if (sell.intValue() < 0) {
            throw new IllegalArgumentException("The sell price value should be a positive number");
        }

        this.sell = sell;
    }

    public void setSell(String sell) {
        setSell(new BigDecimal(sell));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate rate = (ExchangeRate) o;
        return code.equals(rate.code) &&
                Objects.equals(buy, rate.buy) &&
                Objects.equals(sell, rate.sell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, buy, sell);
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "code='" + code + '\'' +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
