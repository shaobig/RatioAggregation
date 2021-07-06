package com.sigma.finance.ratio_agregation.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.repository.packagers.JsonExchangeRatePathPackager;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ExchangeRateRepository {

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

    public void updateExchangeRate(String bankName, ExchangeRate exchangeRate) {
        List<ExchangeRate> rates = readExchangeRatesByBankName(bankName);

        if (!rates.isEmpty()) {
            int index = rates.stream()
                    .filter(f -> f.getCode().equals(exchangeRate.getCode()))
                    .map(rates::indexOf)
                    .findFirst()
                    .orElse(-1);
            rates.set(index, exchangeRate);

            ObjectMapper mapper = new ObjectMapper();

            try {
                String path = new JsonExchangeRatePathPackager().getExchangeRatePath(bankName);
                mapper.writeValue(new FileOutputStream(path), rates);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteExchangeRates(String bankName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String path = new JsonExchangeRatePathPackager().getExchangeRatePath(bankName);
            mapper.writeValue(new FileOutputStream(path), new ArrayList<>());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
