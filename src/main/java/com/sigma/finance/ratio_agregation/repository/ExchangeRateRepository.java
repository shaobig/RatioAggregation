package com.sigma.finance.ratio_agregation.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.repository.packagers.JsonExchangeRatePathPackager;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ExchangeRateRepository {

    public List<ExchangeRate> readAllExchangeRates() {
        List<ExchangeRate> rates = new ArrayList<>();

        for (BankName bankName : BankName.values()) {
            rates.addAll(readExchangeRatesByBankName(String.valueOf(bankName)));
        }

        return rates;
    }

    public List<ExchangeRate> readExchangeRatesByBankName(String bankName) {
        ObjectMapper mapper = new ObjectMapper();
        List<ExchangeRate> rates = new ArrayList<>();

        try {
            String path = new JsonExchangeRatePathPackager().getExchangeRatePath(bankName);
            rates = Arrays.asList(mapper.readValue(new FileInputStream(path), ExchangeRate[].class));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return rates;
    }
}
