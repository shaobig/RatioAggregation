package com.sigma.finance.ratio_agregation.comparators;

import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;

import java.util.Comparator;

public class ExchangeRateDtoAscendingComparator implements Comparator<ExchangeRateDto> {

    @Override
    public int compare(ExchangeRateDto rateOne, ExchangeRateDto rateTwo) {
        return rateOne != null && rateTwo != null
                ? rateOne.getPrice().compareTo(rateTwo.getPrice())
                : 0;
    }
}
