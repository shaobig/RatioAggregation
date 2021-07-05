package com.sigma.finance.ratio_agregation.services;

import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;
import com.sigma.finance.ratio_agregation.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRateDto> getAllExchangeRates() {
        List<ExchangeRateDto> ratesDto = new ArrayList<>();

        for (BankName bankName : BankName.values()) {
            ratesDto.addAll(getExchangeRatesByBankName(String.valueOf(bankName)));
        }

        return ratesDto;
    }

    public List<ExchangeRateDto> getExchangeRatesByBankName(String bankName) {
        List<ExchangeRate> rates = exchangeRateRepository.readExchangeRatesByBankName(bankName);
        List<ExchangeRateDto> ratesDto = new ArrayList<>();

        rates.forEach(exchangeRate ->
                ratesDto.add(new ExchangeRateDto(bankName, exchangeRate))
        );

        return ratesDto;
    }
}