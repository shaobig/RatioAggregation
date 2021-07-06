package com.sigma.finance.ratio_agregation.services;

import com.sigma.finance.ratio_agregation.comparators.ExchangeRateDtoAscendingComparator;
import com.sigma.finance.ratio_agregation.comparators.SortingOrderExchangeRateDtoComparatorFactory;
import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.entities.SortingOrder;
import com.sigma.finance.ratio_agregation.entities.dto.BankExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.ReportExchangeRateDto;
import com.sigma.finance.ratio_agregation.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public BankExchangeRateDto createExchangeRate(String bankName, ExchangeRate exchangeRate) {
        exchangeRateRepository.createExchangeRate(bankName, exchangeRate);
        return new BankExchangeRateDto(bankName, exchangeRate);
    }

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

    public List<ExchangeRateDto> getBuyExchangeRatesFilteredByCurrencyCode(String code, Comparator<ExchangeRateDto> comparator) {
        return getAllExchangeRates().stream()
                .filter(f -> f.getExchangeRate().getBuy() != null)
                .filter(f -> f.getExchangeRate().getCode().equals(code.toUpperCase()))
                .map(m -> new ExchangeRateDto(m.getBankName(), m.getExchangeRate().getCode(), m.getExchangeRate().getBuy()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<ExchangeRateDto> getBuyExchangeRatesFilteredByCurrencyCode(String code, String sortingOrder) {
        Comparator<ExchangeRateDto> comparator
                = new SortingOrderExchangeRateDtoComparatorFactory()
                .getExchangeRateComparator(SortingOrder.valueOf(sortingOrder.toUpperCase()));

        return getBuyExchangeRatesFilteredByCurrencyCode(code, comparator);
    }

    public List<ExchangeRateDto> getBuyExchangeRatesFilteredByCurrencyCode(String code) {
        return getBuyExchangeRatesFilteredByCurrencyCode(code, new ExchangeRateDtoAscendingComparator());
    }

    public List<ExchangeRateDto> getSellExchangeRatesFilteredByCurrencyCode(String code, Comparator<ExchangeRateDto> comparator) {
        return getAllExchangeRates().stream()
                .filter(f -> f.getExchangeRate().getSell() != null)
                .filter(f -> f.getExchangeRate().getCode().equals(code.toUpperCase()))
                .map(m -> new ExchangeRateDto(m.getBankName(), m.getExchangeRate().getCode(), m.getExchangeRate().getSell()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public List<ExchangeRateDto> getSellExchangeRatesFilteredByCurrencyCode(String code, String sortingOrder) {
        Comparator<ExchangeRateDto> comparator
                = new SortingOrderExchangeRateDtoComparatorFactory()
                .getExchangeRateComparator(SortingOrder.valueOf(sortingOrder.toUpperCase()));

        return getSellExchangeRatesFilteredByCurrencyCode(code, comparator);
    }

    public List<ExchangeRateDto> getSellExchangeRatesFilteredByCurrencyCode(String code) {
        return getSellExchangeRatesFilteredByCurrencyCode(code, new ExchangeRateDtoAscendingComparator());
    }

    public BankExchangeRateDto updateExchangeRate(String bankName, ExchangeRate exchangeRate) {
        exchangeRateRepository.updateExchangeRate(bankName, exchangeRate);
        return new BankExchangeRateDto(bankName, exchangeRate);
    }

    public List<BankExchangeRateDto> deleteExchangeRate(String bankName) {
        List<BankExchangeRateDto> deletedRates = getExchangeRatesByBankName(bankName);
        exchangeRateRepository.deleteExchangeRates(bankName);

        return deletedRates;
    }

    public List<ReportExchangeRateDto> getOptimumExchangeRatesReport() {
        List<String> codes = getAllExchangeRates().stream()
                .map(m -> m.getExchangeRate().getCode())
                .distinct()
                .collect(Collectors.toList());

        List<ReportExchangeRateDto> reportRates = new ArrayList<>();

        if (!codes.isEmpty()) {
            codes.forEach(c -> {
                ReportExchangeRateDto reportExchangeRateDto = new ReportExchangeRateDto();
                reportExchangeRateDto.setCode(c);

                ExchangeRateDto exchangeRate = getBuyExchangeRatesFilteredByCurrencyCode(c).stream()
                        .min(Comparator.comparing(ExchangeRateDto::getPrice))
                        .orElse(new ExchangeRateDto(c, new BigDecimal("0.0")));

                reportExchangeRateDto.setBuy(exchangeRate.getPrice());
                reportExchangeRateDto.setBuyBankName(exchangeRate.getBankName());

                exchangeRate = getSellExchangeRatesFilteredByCurrencyCode(c).stream()
                        .min(Comparator.comparing(ExchangeRateDto::getPrice))
                        .orElse(new ExchangeRateDto(c, new BigDecimal("0.0")));

                reportExchangeRateDto.setSell(exchangeRate.getPrice());
                reportExchangeRateDto.setSellBankName(exchangeRate.getBankName());

                reportRates.add(reportExchangeRateDto);
            });
        }

        return reportRates;
    }
}