package com.sigma.finance.ratio_agregation.comparators;

import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;

import java.util.Comparator;

public class ExchangeRateDtoDescendingComparator implements Comparator<ExchangeRateDto> {

    @Override
    public int compare(ExchangeRateDto rateOne, ExchangeRateDto rateTwo) {
        return rateTwo.getPrice().compareTo(rateOne.getPrice());
    }
}
