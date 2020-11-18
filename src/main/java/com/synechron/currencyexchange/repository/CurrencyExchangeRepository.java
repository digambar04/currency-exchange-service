package com.synechron.currencyexchange.repository;

import com.synechron.currencyexchange.pojos.CurrencyExchangeRateMonthly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Currency exchange repository.
 */
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeRateMonthly, Long> {

    List<CurrencyExchangeRateMonthly> findByMonth(String name);
}
