package com.synechron.currencyexchange.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The type Rates.
 */
@Data
public class Rates {
    @JsonProperty("GBP")
    private String gbp;
    @JsonProperty("USD")
    private String usd;
    @JsonProperty("HKD")
    private String hkd;
}
