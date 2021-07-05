package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;

import java.math.BigDecimal;

public class ExchangeRateDto {

    private BankName bankName;
    private String code;
    private BigDecimal price;

    public ExchangeRateDto(BankName bankName, String code, BigDecimal price) {
        this.bankName = bankName;
        this.code = code;
        this.price = price;
    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        this.bankName = bankName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
