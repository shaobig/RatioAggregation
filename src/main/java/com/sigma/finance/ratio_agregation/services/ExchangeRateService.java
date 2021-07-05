package com.sigma.finance.ratio_agregation.services;

import com.sigma.finance.ratio_agregation.comparators.SortingOrderExchangeRateDtoComparatorFactory;
import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.entities.SortingOrder;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.BankExchangeRateDto;
import com.sigma.finance.ratio_agregation.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public List<BankExchangeRateDto> getAllExchangeRates() {
        List<BankExchangeRateDto> ratesDto = new ArrayList<>();

        for (BankName bankName : BankName.values()) {
            ratesDto.addAll(getExchangeRatesByBankName(String.valueOf(bankName)));
        }

        return ratesDto;
    }

    public List<BankExchangeRateDto> getExchangeRatesByBankName(String bankName) {
        List<ExchangeRate> rates = exchangeRateRepository.readExchangeRatesByBankName(bankName);
        List<BankExchangeRateDto> ratesDto = new ArrayList<>();

        rates.forEach(exchangeRate ->
                ratesDto.add(new BankExchangeRateDto(bankName, exchangeRate))
        );

        return ratesDto;
    }

    public List<ExchangeRateDto> getBuyExchangeRatesFilteredByCurrencyCode(String code, String sortingOrder) {
        Comparator<ExchangeRateDto> comparator
                = new SortingOrderExchangeRateDtoComparatorFactory()
                .getExchangeRateComparator(SortingOrder.valueOf(sortingOrder.toUpperCase()));

        return getAllExchangeRates().stream()
                .filter(f -> f.getExchangeRate().getCode().equals(code.toUpperCase()))
                .map(m -> new ExchangeRateDto(m.getBankName(), m.getExchangeRate().getCode(), m.getExchangeRate().getBuy()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<ExchangeRateDto> getSellExchangeRatesFilteredByCurrencyCode(String code, String sortingOrder) {
        Comparator<ExchangeRateDto> comparator
                = new SortingOrderExchangeRateDtoComparatorFactory()
                .getExchangeRateComparator(SortingOrder.valueOf(sortingOrder.toUpperCase()));

        return getAllExchangeRates().stream()
                .filter(f -> f.getExchangeRate().getCode().equals(code.toUpperCase()))
                .map(m -> new ExchangeRateDto(m.getBankName(), m.getExchangeRate().getCode(), m.getExchangeRate().getSell()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}