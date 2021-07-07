package com.sigma.finance.ratio_agregation.services;

import com.sigma.finance.ratio_agregation.comparators.ExchangeRateDtoDescendingComparator;
import com.sigma.finance.ratio_agregation.entities.BankName;
import com.sigma.finance.ratio_agregation.entities.ExchangeRate;
import com.sigma.finance.ratio_agregation.entities.SortingOrder;
import com.sigma.finance.ratio_agregation.entities.dto.BankExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.ExchangeRateDto;
import com.sigma.finance.ratio_agregation.entities.dto.ReportExchangeRateDto;
import com.sigma.finance.ratio_agregation.repository.ExchangeRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateRepository rateRepository;
    @InjectMocks
    private ExchangeRateService rateService;

    private static final ExchangeRate EXCHANGE_RATE_APOSTLE_ONE
            = new ExchangeRate("USD", "25.05", "27.45");
    private static final ExchangeRate EXCHANGE_RATE_APOSTLE_TWO
            = new ExchangeRate("EUR", "27.05", "29.54");
    private static final ExchangeRate EXCHANGE_RATE_APOSTLE_THREE
            = new ExchangeRate("JPY", "0.25", "0.45");
    private static final ExchangeRate EXCHANGE_RATE_MONOLITH_ONE
            = new ExchangeRate("USD", "26.5", "27.05");
    private static final ExchangeRate EXCHANGE_RATE_MONOLITH_TWO
            = new ExchangeRate("EUR", "28.05", "30.1");
    private static final ExchangeRate EXCHANGE_RATE_NORDBANK_ONE
            = new ExchangeRate("SWE", "3.05", "3.41");
    private static final ExchangeRate EXCHANGE_RATE_SCIENTIFIC_BANK_ONE
            = new ExchangeRate("SWE", "3.18", "3.62");
    private static final ExchangeRate EXCHANGE_RATE_SCIENTIFIC_BANK_TWO
            = new ExchangeRate("GBP", "37.05", "37.95");

    private static final List<ExchangeRate> EXCHANGE_RATE_APOSTLE_LIST = Arrays.asList(
            EXCHANGE_RATE_APOSTLE_ONE,
            EXCHANGE_RATE_APOSTLE_TWO,
            EXCHANGE_RATE_APOSTLE_THREE
    );

    private static final List<ExchangeRate> EXCHANGE_RATE_MONOLITH_LIST = Arrays.asList(
            EXCHANGE_RATE_MONOLITH_ONE,
            EXCHANGE_RATE_MONOLITH_TWO
    );

    private static final List<ExchangeRate> EXCHANGE_RATE_NORDBANK_LIST = Arrays.asList(
            EXCHANGE_RATE_NORDBANK_ONE
    );

    private static final List<ExchangeRate> EXCHANGE_RATE_SCIENTIFIC_BANK_LIST = Arrays.asList(
            EXCHANGE_RATE_SCIENTIFIC_BANK_ONE,
            EXCHANGE_RATE_SCIENTIFIC_BANK_TWO
    );

    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE
            = new BankExchangeRateDto(BankName.APOSTLE, EXCHANGE_RATE_APOSTLE_ONE);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_APOSTLE_TWO
            = new BankExchangeRateDto(BankName.APOSTLE, EXCHANGE_RATE_APOSTLE_TWO);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_APOSTLE_THREE
            = new BankExchangeRateDto(BankName.APOSTLE, EXCHANGE_RATE_APOSTLE_THREE);

    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE
            = new BankExchangeRateDto(BankName.MONOLITH, EXCHANGE_RATE_MONOLITH_ONE);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_MONOLITH_TWO
            = new BankExchangeRateDto(BankName.MONOLITH, EXCHANGE_RATE_MONOLITH_TWO);

    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_NORDBANK_ONE
            = new BankExchangeRateDto(BankName.NORDBANK, EXCHANGE_RATE_NORDBANK_ONE);

    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_SCIENTIFIC_BANK_ONE
            = new BankExchangeRateDto(BankName.SCIENTIFIC_BANK, EXCHANGE_RATE_SCIENTIFIC_BANK_ONE);
    private static final BankExchangeRateDto BANK_EXCHANGE_RATE_DTO_SCIENTIFIC_BANK_TWO
            = new BankExchangeRateDto(BankName.SCIENTIFIC_BANK, EXCHANGE_RATE_SCIENTIFIC_BANK_TWO);

    private static final List<BankExchangeRateDto> BANK_EXCHANGE_RATE_DTO_APOSTLE_LIST = Arrays.asList(
            BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE,
            BANK_EXCHANGE_RATE_DTO_APOSTLE_TWO,
            BANK_EXCHANGE_RATE_DTO_APOSTLE_THREE
    );

    private static final List<BankExchangeRateDto> BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST = Arrays.asList(
            BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE,
            BANK_EXCHANGE_RATE_DTO_MONOLITH_TWO
    );

    private static final List<BankExchangeRateDto> BANK_EXCHANGE_RATE_DTO_LIST = Arrays.asList(
            BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE,
            BANK_EXCHANGE_RATE_DTO_APOSTLE_TWO,
            BANK_EXCHANGE_RATE_DTO_APOSTLE_THREE,
            BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE,
            BANK_EXCHANGE_RATE_DTO_MONOLITH_TWO,
            BANK_EXCHANGE_RATE_DTO_NORDBANK_ONE,
            BANK_EXCHANGE_RATE_DTO_SCIENTIFIC_BANK_ONE,
            BANK_EXCHANGE_RATE_DTO_SCIENTIFIC_BANK_TWO
    );

    private static final List<ExchangeRateDto> BUY_EXCHANGE_RATE_DTO_USD = Arrays.asList(
            new ExchangeRateDto(
                    BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE.getBankName(),
                    BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE.getExchangeRate().getCode(),
                    BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE.getExchangeRate().getBuy()
            ),
            new ExchangeRateDto(
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getBankName(),
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getExchangeRate().getCode(),
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getExchangeRate().getBuy()
            )
    );

    private static final List<ExchangeRateDto> SELL_EXCHANGE_RATE_DTO_USD = Arrays.asList(
            new ExchangeRateDto(
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getBankName(),
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getExchangeRate().getCode(),
                    BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE.getExchangeRate().getSell()
            ),
            new ExchangeRateDto(
                    BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE.getBankName(),
                    BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE.getExchangeRate().getCode(),
                    BANK_EXCHANGE_RATE_DTO_APOSTLE_ONE.getExchangeRate().getSell()
            )
    );

//    private static final List<String> CURRENCY_CODES = Arrays.asList("USD","EUR","JPY","SWE","GBP");

    private static final List<ReportExchangeRateDto> REPORT_EXCHANGE_RATE_LIST = Arrays.asList(
            new ReportExchangeRateDto(
                    "USD",
                    EXCHANGE_RATE_APOSTLE_ONE.getBuy(),
                    BankName.APOSTLE,
                    EXCHANGE_RATE_MONOLITH_ONE.getSell(),
                    BankName.MONOLITH
                    ),
            new ReportExchangeRateDto(
                    "EUR",
                    EXCHANGE_RATE_APOSTLE_TWO.getBuy(),
                    BankName.APOSTLE,
                    EXCHANGE_RATE_APOSTLE_TWO.getSell(),
                    BankName.APOSTLE
            ),
            new ReportExchangeRateDto(
                    "JPY",
                    EXCHANGE_RATE_APOSTLE_THREE.getBuy(),
                    BankName.APOSTLE,
                    EXCHANGE_RATE_APOSTLE_THREE.getSell(),
                    BankName.APOSTLE
            ),
            new ReportExchangeRateDto(
                    "SWE",
                    EXCHANGE_RATE_NORDBANK_ONE.getBuy(),
                    BankName.NORDBANK,
                    EXCHANGE_RATE_NORDBANK_ONE.getSell(),
                    BankName.NORDBANK
            ),
            new ReportExchangeRateDto(
                    "GBP",
                    EXCHANGE_RATE_SCIENTIFIC_BANK_TWO.getBuy(),
                    BankName.SCIENTIFIC_BANK,
                    EXCHANGE_RATE_SCIENTIFIC_BANK_TWO.getSell(),
                    BankName.SCIENTIFIC_BANK
            )
    );

    @Test
    public void testGetExchangeRateByBankNameEqualsApostle() {
        String bankName = BankName.APOSTLE.toString();

        when(rateRepository.readExchangeRatesByBankName(eq(bankName)))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);

        assertEquals(BANK_EXCHANGE_RATE_DTO_APOSTLE_LIST.size(), rateRepository.readExchangeRatesByBankName(bankName).size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_APOSTLE_LIST, rateService.getExchangeRatesByBankName(bankName));
    }

    @Test
    public void testGetExchangeRatesByBankNameEqualsMonolith() {
        String bankName = BankName.MONOLITH.toString();

        when(rateRepository.readExchangeRatesByBankName(eq(bankName)))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);

        assertEquals(BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST.size(), rateRepository.readExchangeRatesByBankName(bankName).size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST, rateService.getExchangeRatesByBankName(bankName));
    }

    @Test
    public void testGetAllExchangeRates() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.NORDBANK.toString())))
                .thenReturn(EXCHANGE_RATE_NORDBANK_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.SCIENTIFIC_BANK.toString())))
                .thenReturn(EXCHANGE_RATE_SCIENTIFIC_BANK_LIST);

        assertEquals(EXCHANGE_RATE_APOSTLE_LIST.size(), rateRepository
                .readExchangeRatesByBankName(BankName.APOSTLE.toString()).size());
        assertEquals(EXCHANGE_RATE_MONOLITH_LIST.size(), rateRepository
                .readExchangeRatesByBankName(BankName.MONOLITH.toString()).size());

        assertEquals(BANK_EXCHANGE_RATE_DTO_LIST.size(), rateService.getAllExchangeRates().size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_LIST, rateService.getAllExchangeRates());
    }

    @Test
    public void testGetBuyExchangeRatesFilteredByCurrencyCode() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);

        assertEquals(BUY_EXCHANGE_RATE_DTO_USD.size(), rateService
                .getBuyExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.ASCENDING.toString()).size());
        assertEquals(BUY_EXCHANGE_RATE_DTO_USD, rateService
                .getBuyExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.ASCENDING.toString()));
    }

    @Test
    public void testGetBuyExchangeRatesFilteredByCurrencyCodeDescendingOrder() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);

        List<ExchangeRateDto> reversed = new ArrayList<>(BUY_EXCHANGE_RATE_DTO_USD);
        reversed.sort(new ExchangeRateDtoDescendingComparator());

        assertEquals(reversed.size(), rateService
                .getBuyExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.DESCENDING.toString()).size());
        assertEquals(reversed, rateService
                .getBuyExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.DESCENDING.toString()));
    }

    @Test
    public void testGetSellExchangeRatesFilteredByCurrencyCode() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);

        assertEquals(SELL_EXCHANGE_RATE_DTO_USD.size(), rateService
                .getSellExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.ASCENDING.toString()).size());
        assertEquals(SELL_EXCHANGE_RATE_DTO_USD, rateService
                .getSellExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.ASCENDING.toString()));
    }

    @Test
    public void testGetSellExchangeRatesFilteredByCurrencyCodeDescendingOrder() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);

        List<ExchangeRateDto> reversed = new ArrayList<>(SELL_EXCHANGE_RATE_DTO_USD);
        reversed.sort(new ExchangeRateDtoDescendingComparator());

        assertEquals(reversed.size(), rateService
                .getSellExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.DESCENDING.toString()).size());
        assertEquals(reversed, rateService
                .getSellExchangeRatesFilteredByCurrencyCode("USD", SortingOrder.DESCENDING.toString()));
    }

    @Test
    public void testUpdateExchangeRate() {
        assertEquals(
                BANK_EXCHANGE_RATE_DTO_MONOLITH_ONE,
                rateService.updateExchangeRate(BankName.MONOLITH.toString(), EXCHANGE_RATE_MONOLITH_ONE)
        );
    }

    @Test
    public void testDeleteExchangeRates() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);

        assertEquals(BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST.size(),
                rateService.deleteExchangeRate(BankName.MONOLITH.toString()).size());
        assertEquals(BANK_EXCHANGE_RATE_DTO_MONOLITH_LIST,
                rateService.deleteExchangeRate(BankName.MONOLITH.toString()));
    }

    @Test
    public void testGetOptimumExchangeRatesReport() {
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.APOSTLE.toString())))
                .thenReturn(EXCHANGE_RATE_APOSTLE_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.MONOLITH.toString())))
                .thenReturn(EXCHANGE_RATE_MONOLITH_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.NORDBANK.toString())))
                .thenReturn(EXCHANGE_RATE_NORDBANK_LIST);
        when(rateRepository.readExchangeRatesByBankName(eq(BankName.SCIENTIFIC_BANK.toString())))
                .thenReturn(EXCHANGE_RATE_SCIENTIFIC_BANK_LIST);

        assertEquals(REPORT_EXCHANGE_RATE_LIST.size(), rateService.getOptimumExchangeRatesReport().size());
        assertEquals(REPORT_EXCHANGE_RATE_LIST, rateService.getOptimumExchangeRatesReport());
    }
}
