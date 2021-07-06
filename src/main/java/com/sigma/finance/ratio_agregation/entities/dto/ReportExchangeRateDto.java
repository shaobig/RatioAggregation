package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;

import java.math.BigDecimal;

public class ReportExchangeRateDto {

    private String code;

    private BigDecimal buy;
    private BankName buyBankName;

    private BigDecimal sell;
    private BankName sellBankName;

    public ReportExchangeRateDto() {}

    public ReportExchangeRateDto(String code, BigDecimal buy, BankName buyBankName, BigDecimal sell, BankName sellBankName) {
        this.code = code;
        this.buy = buy;
        this.buyBankName = buyBankName;
        this.sell = sell;
        this.sellBankName = sellBankName;
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

    public BankName getBuyBankName() {
        return buyBankName;
    }

    public void setBuyBankName(BankName buyBankName) {
        this.buyBankName = buyBankName;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BankName getSellBankName() {
        return sellBankName;
    }

    public void setSellBankName(BankName sellBankName) {
        this.sellBankName = sellBankName;
    }
}
