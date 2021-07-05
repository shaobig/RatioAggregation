package com.sigma.finance.ratio_agregation.services;

import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepository.readAllExchangeRates();
    }

    public List<ExchangeRate> getExchangeRatesByBankName(String bankName) {
        return exchangeRateRepository.readExchangeRatesByBankName(bankName);
    }
}