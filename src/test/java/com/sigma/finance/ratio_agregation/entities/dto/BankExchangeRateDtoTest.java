package com.sigma.finance.ratio_agregation.entities.dto;

import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankExchangeRateDtoTest {

    private static BankExchangeRateDto bankExchangeRateDto;

    @BeforeAll
    public static void setUp() {
        bankExchangeRateDto = new BankExchangeRateDto(BankName.APOSTLE, new ExchangeRate("POL", "7.15", "7.41"));
    }

    @Test
    public void setBankNameEqualsNull() {
        assertThrows(NullPointerException.class, () -> bankExchangeRateDto.setBankName(null));
    }

    @Test
    public void setBankName() {
        BankName bankName = BankName.MONOLITH;
        bankExchangeRateDto.setBankName(bankName);

        assertEquals(bankName, bankExchangeRateDto.getBankName());
    }

    @Test
    public void setExchangeRateEqualsNull() {
        assertThrows(NullPointerException.class, () -> bankExchangeRateDto.setExchangeRate(null));
    }

    @Test
    public void setExchangeRate() {
        ExchangeRate rate = new ExchangeRate("PLN", "7.05", "7.35");
        bankExchangeRateDto.setExchangeRate(rate);

        assertEquals(rate, bankExchangeRateDto.getExchangeRate());
    }
}
