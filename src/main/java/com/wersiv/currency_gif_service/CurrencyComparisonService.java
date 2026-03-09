package com.wersiv.currency_gif_service;

import com.wersiv.currency_gif_service.feign.OxrClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Slf4j
@Component
public class CurrencyComparisonService {

    @Value("${oxr.app_id}")
    private String appId;

    @Value("${oxr.current_currency}")
    private String currency;


    private final OxrClient oxrClient;

    public CurrencyComparisonService(OxrClient oxrClient) {
        this.oxrClient = oxrClient;
    }


    public String getCurrencyTrendTag() {

            LocalDate today = LocalDate.now();
            LocalDate yesterday = today.minusDays(1);

            double todayRate = oxrClient.getLatestRates(appId).getRates().get(currency);
            double yesterdayRate = oxrClient.getHistoricalRates(yesterday.toString(), appId).getRates().get(currency);

            log.info("currency today: {}, yesterday: {}", todayRate, yesterdayRate);
            return (todayRate >= yesterdayRate) ? "rich" : "broke";

    }
}
