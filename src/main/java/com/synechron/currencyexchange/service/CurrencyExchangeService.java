package com.synechron.currencyexchange.service;

import com.synechron.currencyexchange.pojos.CurrencyExchangeRateMonthly;
import com.synechron.currencyexchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Currency exchange service.
 */
@Service
public class CurrencyExchangeService {
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    /**
     * Gets historic rates.
     *
     * @return the historic rates
     */
    public List<CurrencyExchangeRateMonthly> getHistoricRates() {
        return currencyExchangeRepository.findAll();
    }

    public List<CurrencyExchangeRateMonthly> getLatestRate() {
        return currencyExchangeRepository.findByMonth(LocalDate.now().minusMonths(1).getMonth().name());
    }
}
