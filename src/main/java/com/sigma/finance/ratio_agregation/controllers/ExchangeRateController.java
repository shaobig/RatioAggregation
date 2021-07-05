package com.sigma.finance.ratio_agregation.controllers;

import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.BankExchangeRateDto;
import com.sigma.finance.ratio_agregation.services.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/rates")
    public List<BankExchangeRateDto> getAllExchangeRates() {
        return exchangeRateService.getAllExchangeRates();
    }

    @GetMapping("/rates/bank/{bankName}")
    public List<BankExchangeRateDto> getAllExchangeRatesByBankName(
            @PathVariable(value = "bankName") String bankName
    ) {
        return exchangeRateService.getExchangeRatesByBankName(bankName);
    }

    @GetMapping("/rates/buy/{code}")
    public List<ExchangeRateDto> getBuyExchangeRatesFilteredByCurrencyCode(
            @PathVariable(value = "code") String code,
            @RequestParam(value = "order", defaultValue = "ASCENDING") String sortingOrder
            ) {

        return exchangeRateService.getBuyExchangeRatesFilteredByCurrencyCode(code, sortingOrder);
    }

    @GetMapping("/rates/sell/{code}")
    public List<ExchangeRateDto> getAllExchangeRatesByCurrencyCode(
            @PathVariable(value = "code") String code,
            @RequestParam(value = "order", defaultValue = "ASCENDING") String sortingOrder
    ) {

        return exchangeRateService.getSellExchangeRatesFilteredByCurrencyCode(code, sortingOrder);
    }
}
