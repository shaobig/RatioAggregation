package com.sigma.finance.ratio_agregation.comparators;

import com.sigma.finance.ratio_agregation.entities.SortingOrder;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;

import java.util.Comparator;

public interface ExchangeRateComparatorFactory {
    Comparator<ExchangeRateDto> getExchangeRateComparator(SortingOrder order);
}
