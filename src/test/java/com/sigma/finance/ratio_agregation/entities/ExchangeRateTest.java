package com.sigma.finance.ratio_agregation.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExchangeRateTest {

    private static ExchangeRate exchangeRate;

    @BeforeAll
    public static void setUp() {
        exchangeRate = new ExchangeRate("EUR", "30.05", "32.25");
    }

    @Test
    public void testSetCodeEqualsNull() {
        assertThrows(NullPointerException.class, () -> exchangeRate.setCode(null));
    }

    @Test
    public void testSetCodeEqualsEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setCode(""));
    }

    @Test
    public void testSetCodeContainsNumbers() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setCode("E5R"));
    }

    @Test
    public void testSetCode() {
        String code = "SEK";
        exchangeRate.setCode(code);

        assertEquals(code, exchangeRate.getCode());
    }

    @Test
    public void testSetBuyEqualsNull() {
        assertThrows(NullPointerException.class, () -> exchangeRate.setBuy((String) null));
    }

    @Test
    public void testSetBuyEqualsEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setBuy(""));
    }

    @Test
    public void testSetBuy() {
        BigDecimal buy = new BigDecimal("29.95");
        exchangeRate.setBuy(buy);

        assertEquals(buy, exchangeRate.getBuy());
    }

    @Test
    public void testSetSellEqualsNull() {
        assertThrows(NullPointerException.class, () -> exchangeRate.setSell((String) null));
    }

    @Test
    public void testSetSellEqualsEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> exchangeRate.setSell(""));
    }

    @Test
    public void testSetSell() {
        BigDecimal sell = new BigDecimal("31.04");
        exchangeRate.setSell(sell);

        assertEquals(sell, exchangeRate.getSell());
    }
}
