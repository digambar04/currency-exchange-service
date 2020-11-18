package com.synechron.currencyexchange.pojos;

import lombok.Data;

/**
 * The type Filtered currency rates.
 */
@Data
public class FilteredCurrencyRates {
    private String base;
    private Rates rates;
    private String date;
}
