package com.sigma.finance.ratio_agregation.entities;

import java.math.BigDecimal;

public class ExchangeRate {
    private String code;
    private BigDecimal buy;
    private BigDecimal sell;

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
}
