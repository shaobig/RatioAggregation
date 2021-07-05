package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExchangeRateDtoTest {

    private static ExchangeRateDto exchangeRateDto;

    @BeforeAll
    public static void setUp() {
        exchangeRateDto = new ExchangeRateDto(BankName.APOSTLE, new ExchangeRate("POL", "7.15", "7.41"));
    }

    @Test
    public void setBankNameEqualsNull() {
        assertThrows(NullPointerException.class, () -> exchangeRateDto.setBankName(null));
    }

    @Test
    public void setBankName() {
        BankName bankName = BankName.MONOLITH;
        exchangeRateDto.setBankName(bankName);

        assertEquals(bankName, exchangeRateDto.getBankName());
    }

    @Test
    public void setExchangeRateEqualsNull() {
        assertThrows(NullPointerException.class, () -> exchangeRateDto.setExchangeRate(null));
    }

    @Test
    public void setExchangeRate() {
        ExchangeRate rate = new ExchangeRate("PLN", "7.05", "7.35");
        exchangeRateDto.setExchangeRate(rate);

        assertEquals(rate, exchangeRateDto.getExchangeRate());
    }
}
