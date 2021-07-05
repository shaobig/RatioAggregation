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
        this.code = code;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
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
}
