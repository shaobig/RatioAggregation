package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;

import java.math.BigDecimal;
import java.util.Objects;

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

    public ReportExchangeRateDto(String code, String buy, BankName buyBankName, String sell, BankName sellBankName) {
        this.code = code;
        this.buy = new BigDecimal(buy);
        this.buyBankName = buyBankName;
        this.sell = new BigDecimal(sell);
        this.sellBankName = sellBankName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportExchangeRateDto that = (ReportExchangeRateDto) o;
        return code.equals(that.code) &&
                Objects.equals(buy, that.buy) &&
                buyBankName == that.buyBankName &&
                Objects.equals(sell, that.sell) &&
                sellBankName == that.sellBankName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, buy, buyBankName, sell, sellBankName);
    }
}
