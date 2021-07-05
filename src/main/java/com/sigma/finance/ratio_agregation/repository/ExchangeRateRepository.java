package com.sigma.finance.ratio_agregation.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.factories.path.JsonExchangeRatesPathFactory;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ExchangeRateRepository {

    public List<ExchangeRate> readAllExchangeRates() {
        ObjectMapper mapper = new ObjectMapper();
        List<ExchangeRate> rates = new ArrayList<>();

        try {
            for (BankName bankName : BankName.values()) {
                String path = new JsonExchangeRatesPathFactory().getExchangeRatesPath(String.valueOf(bankName));
                rates.addAll(
                        Arrays.asList(mapper.readValue(new FileInputStream(path), ExchangeRate[].class))
                );
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return rates;
    }

    public List<ExchangeRate> readExchangeRatesByBankName(String bankName) {
        ObjectMapper mapper = new ObjectMapper();
        List<ExchangeRate> rates = new ArrayList<>();

        try {
            String path = new JsonExchangeRatesPathFactory().getExchangeRatesPath(bankName);
            rates = Arrays.asList(mapper.readValue(new FileInputStream(path), ExchangeRate[].class));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return rates;
    }
}
