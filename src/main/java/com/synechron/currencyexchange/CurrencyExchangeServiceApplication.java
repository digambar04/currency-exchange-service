package com.synechron.currencyexchange;

import com.synechron.currencyexchange.pojos.CurrencyExchangeRateMonthly;
import com.synechron.currencyexchange.pojos.FilteredCurrencyRates;
import com.synechron.currencyexchange.pojos.User;
import com.synechron.currencyexchange.repository.CurrencyExchangeRepository;
import com.synechron.currencyexchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The type Currency exchange service application.
 */
@SpringBootApplication
@EnableSwagger2
public class CurrencyExchangeServiceApplication {

    @Value("${currency.base}")
    private String currencyBase;

    @Value("${currency.symbols}")
    private String currencySymbols;

    @Value("${currency.api-url}")
    private String apiUrl;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * The Client.
     */
    @Autowired
    WebClient client;

    /**
     * Fetch last six month rates and, save currency rates month wise to in memory db at the start of program
     */
    @PostConstruct
    public void init() {
        LocalDate localDate = LocalDate.now();

        List<CurrencyExchangeRateMonthly> monthlyCurrencyList = new ArrayList();
        //Get all rates of last six months
        for (int i = 1; i <= 6; i++) {


            LocalDate localDate1 = localDate.minusMonths(i);
            LocalDate historicMonthDate = localDate1.withDayOfMonth(1);

            FilteredCurrencyRates filteredCurrencyRates = client.get()
                    .uri(builder -> builder
                            .path("api/{date}")
                            .queryParam("base", currencyBase)
                            .queryParam("symbols", currencySymbols)
                            .build(historicMonthDate))
                    .retrieve()
                    .bodyToMono(FilteredCurrencyRates.class).block();

            CurrencyExchangeRateMonthly currencyExchangeRateMonthly = new CurrencyExchangeRateMonthly();
            currencyExchangeRateMonthly.setMonth(historicMonthDate.getMonth().name());
            currencyExchangeRateMonthly.setCurrency("GBP");
            currencyExchangeRateMonthly.setRate(filteredCurrencyRates.getRates().getGbp());

            monthlyCurrencyList.add(currencyExchangeRateMonthly);

            CurrencyExchangeRateMonthly currencyExchangeRateMonthly1 = new CurrencyExchangeRateMonthly();
            currencyExchangeRateMonthly1.setMonth(historicMonthDate.getMonth().name());
            currencyExchangeRateMonthly1.setCurrency("USD");
            currencyExchangeRateMonthly1.setRate(filteredCurrencyRates.getRates().getUsd());

            monthlyCurrencyList.add(currencyExchangeRateMonthly1);

            CurrencyExchangeRateMonthly currencyExchangeRateMonthly2 = new CurrencyExchangeRateMonthly();
            currencyExchangeRateMonthly2.setMonth(historicMonthDate.getMonth().name());
            currencyExchangeRateMonthly2.setCurrency("HKD");
            currencyExchangeRateMonthly2.setRate(filteredCurrencyRates.getRates().getHkd());
            monthlyCurrencyList.add(currencyExchangeRateMonthly2);
        }
        currencyExchangeRepository.saveAll(monthlyCurrencyList);

        List<User> users = Stream.of(new User(1, "admin", "admin")).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
    }

    /**
     * Gets web client.
     *
     * @return the web client
     */
    @Bean
    WebClient getWebClient() {
        return WebClient.builder().baseUrl(apiUrl).build();
    }
}
