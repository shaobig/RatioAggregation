package com.sigma.finance.ratio_agregation.services;

import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.entities.SortingOrder;
import com.sigma.finance.ratio_agregation.entities.dto.BankExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;
import com.sigma.finance.ratio_agregation.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateRepository rateRepository;
    @InjectMocks
    private ExchangeRateService rateService;

    private static final ExchangeRate EXCHANGE_RATE_APOSTOL_ONE
            = new ExchangeRate("USD", "25.05", "27.45");
    private static final ExchangeRate EXCHANGE_RATE_APOSTOL_TWO
            = new ExchangeRate("EUR", "27.05", "29.54");
    private static final ExchangeRate EXCHANGE_RATE_APOSTOL_THREE
            = new ExchangeRate("JPY", "0.25", "0.45");
    private static final ExchangeRate EXCHANGE_RATE_MONOLITH_ONE
            = new ExchangeRate("USD", "27.5", "29.5");
    private static final ExchangeRate EXCHANGE_RATE_MONOLITH_TWO
            = new ExchangeRate("EUR", "28.05", "30.1");

    private static final List<ExchangeRate> RATE_EXCHANGE_APOSTOL_LIST = Arrays.asList(
            EXCHANGE_RATE_APOSTOL_ONE,
            EXCHANGE_RATE_APOSTOL_TWO,
            EXCHANGE_RATE_APOSTOL_THREE
    );

    private static final List<ExchangeRate> RATE_EXCHANGE_MONOLITH_LIST = Arrays.asList(
            EXCHANGE_RATE_MONOLITH_ONE,
            EXCHANGE_RATE_MONOLITH_TWO
    );

    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_APOSTOL_ONE
            = new BankExchangeRateDto(BankName.APOSTLE, EXCHANGE_RATE_APOSTOL_ONE);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_APOSTOL_TWO
            = new BankExchangeRateDto(BankName.APOSTLE, EXCHANGE_RATE_APOSTOL_TWO);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_APOSTOL_THREE
            = new BankExchangeRateDto(BankName.APOSTLE, EXCHANGE_RATE_APOSTOL_THREE);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE
            = new BankExchangeRateDto(BankName.MONOLITH, EXCHANGE_RATE_MONOLITH_ONE);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_MONOLITH_TWO
            = new BankExchangeRateDto(BankName.MONOLITH, EXCHANGE_RATE_MONOLITH_TWO);

    private static final List<BankExchangeRateDto> BANK_EXCHANGE_RATE_DTO_APOSTLE_LIST = Arrays.asList(
            BANK_EXCHANGE_RATE_DTO_APOSTOL_ONE,
            BANK_EXCHANGE_RATE_DTO_APOSTOL_TWO,
            BANK_EXCHANGE_RATE_DTO_APOSTOL_THREE
    );

    private static final List<BankExchangeRateDto> BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST = Arrays.asList(
            BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE,
            BANK_EXCHANGE_RATE_DTO_MONOLITH_TWO
    );

    private static final List<BankExchangeRateDto> BANK_EXCHANGE_RATE_DTO_LIST = Arrays.asList(
            BANK_EXCHANGE_RATE_DTO_APOSTOL_ONE,
            BANK_EXCHANGE_RATE_DTO_APOSTOL_TWO,
            BANK_EXCHANGE_RATE_DTO_APOSTOL_THREE,
            BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE,
            BANK_EXCHANGE_RATE_DTO_MONOLITH_TWO
    );

    private static final List<ExchangeRateDto> BUY_EXCHANGE_RATE_DTO_USD = Arrays.asList(
            new ExchangeRateDto(
                    BANK_EXCHANGE_RATE_DTO_APOSTOL_ONE.getBankName(),
                    BANK_EXCHANGE_RATE_DTO_APOSTOL_ONE.getExchangeRate().getCode(),
                    BANK_EXCHANGE_RATE_DTO_APOSTOL_ONE.getExchangeRate().getBuy()
            ),
            new ExchangeRateDto(
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getBankName(),
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getExchangeRate().getCode(),
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getExchangeRate().getBuy()
            )
    );

    @Test
    public void testGetExchangeRateByBankNameEqualsApostle() {
        String bankName = BankName.APOSTLE.toString();

        Mockito.when(rateRepository.readExchangeRatesByBankName(eq(bankName)))
                .thenReturn(RATE_EXCHANGE_APOSTOL_LIST);

        assertEquals(BANK_EXCHANGE_RATE_DTO_APOSTLE_LIST.size(), rateRepository.readExchangeRatesByBankName(bankName).size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_APOSTLE_LIST, rateService.getExchangeRatesByBankName(bankName));
    }

    @Test
    public void testGetExchangeRatesByBankNameEqualsMonolith() {
        String bankName = BankName.MONOLITH.toString();

        Mockito.when(rateRepository.readExchangeRatesByBankName(eq(bankName)))
                .thenReturn(RATE_EXCHANGE_MONOLITH_LIST);

        assertEquals(BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST.size(), rateRepository.readExchangeRatesByBankName(bankName).size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST, rateService.getExchangeRatesByBankName(bankName));
    }

    @Test
    public void testGetAllExchangeRates() {
        Mockito.when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(RATE_EXCHANGE_APOSTOL_LIST);
        Mockito.when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(RATE_EXCHANGE_MONOLITH_LIST);

        assertEquals(RATE_EXCHANGE_APOSTOL_LIST.size(), rateRepository
                .readExchangeRatesByBankName(BankName.APOSTLE.toString()).size());
        assertEquals(RATE_EXCHANGE_MONOLITH_LIST.size(), rateRepository
                .readExchangeRatesByBankName(BankName.MONOLITH.toString()).size());

        assertEquals(BANK_EXCHANGE_RATE_DTO_LIST.size(), rateService.getAllExchangeRates().size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_LIST, rateService.getAllExchangeRates());
    }

    @Test
    public void testGetBuyExchangeRatesFilteredByCurrencyCode() {
        Mockito.when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(RATE_EXCHANGE_APOSTOL_LIST);
        Mockito.when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(RATE_EXCHANGE_MONOLITH_LIST);

        assertEquals(BUY_EXCHANGE_RATE_DTO_USD.size(), rateService
                .getBuyExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.ASCENDING.toString()).size());
        assertEquals(BUY_EXCHANGE_RATE_DTO_USD, rateService
                .getBuyExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.ASCENDING.toString()));
    }
}
