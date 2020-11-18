package com.synechron.currencyexchange.controller;

import com.synechron.currencyexchange.pojos.CurrencyExchangeRateMonthly;
import com.synechron.currencyexchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Currency exchange controller.
 */
@RestController
@RequestMapping("/currency")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    /**
     * Get historic euro currency rates list.
     *
     * @return the list
     */
    @GetMapping("/historic-rates/get")
    public List<CurrencyExchangeRateMonthly> getHistoricEuroCurrencyRates(){
        return currencyExchangeService.getHistoricRates();
    }

    @GetMapping("/latest/get")
    public List<CurrencyExchangeRateMonthly> getLatestRate(){
        return currencyExchangeService.getLatestRate();
    }
}
