package com.sigma.finance.ratio_agregation.controllers;

import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/rates")
    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateService.getAllExchangeRates();
    }

    @GetMapping("/rates/{bankName}")
    public List<ExchangeRate> getAllExchangeRatesByBankName(
            @PathVariable(value = "bankName") String bankName
    ) {
        return exchangeRateService.getExchangeRatesByBankName(bankName);
    }
}
