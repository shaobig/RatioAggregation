package com.sigma.finance.ratio_agregation.comparators;

import com.sigma.finance.ratio_agregation.entities.SortingOrder;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;

import java.util.Comparator;

public class SortingOrderExchangeRateDtoComparatorFactory implements ExchangeRateComparatorFactory {

    @Override
    public Comparator<ExchangeRateDto> getExchangeRateComparator(SortingOrder order) {
        switch (order) {
            case ASCENDING: return new ExchangeRateDtoAscendingComparator();
            case DESCENDING: return new ExchangeRateDtoDescendingComparator();
            default: throw new IllegalArgumentException("The proposed sorting order is not supported");
        }
    }
}
