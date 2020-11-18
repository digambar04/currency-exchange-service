package com.synechron.currencyexchange.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Currency exchange rate monthly.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MONTHLY_CURRENCY_RATE_TBL")
public class CurrencyExchangeRateMonthly {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue
    long id;
    private String currency;
    private String month;
    private String rate;
}
