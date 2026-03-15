package com.wersiv.currency_gif_service.service;


import com.wersiv.currency_gif_service.CurrencyComparisonService;
import com.wersiv.currency_gif_service.feign.OxrClient;
import com.wersiv.currency_gif_service.response.OxrResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyComparisonServiceTest {

    @Mock
    private OxrClient oxrClient;


    @InjectMocks
    private CurrencyComparisonService currencyComparisonService;


    private final String appId = "test-app-id";
    private final String currency = "USD";


    @BeforeEach
    void setUp() {

        setPrivateField(currencyComparisonService, "appId", appId);
        setPrivateField(currencyComparisonService, "currency", currency);
    }

    @Test
    void getCurrencyTrendTag_WhenRateIncreases_ShouldReturnRich() {

        double todayRate = 90.5;
        double yesterdayRate = 90.0;

        mockOxrResponses(todayRate, yesterdayRate);


        String result = currencyComparisonService.getCurrencyTrendTag();


        assertEquals("rich", result);
    }

    @Test
    void getCurrencyTrendTag_WhenRateDecreases_ShouldReturnBroke() {

        double todayRate = 89.5;
        double yesterdayRate = 90.0;

        mockOxrResponses(todayRate, yesterdayRate);


        String result = currencyComparisonService.getCurrencyTrendTag();


        assertEquals("broke", result);
    }

    @Test
    void getCurrencyTrendTag_WhenRateEqual_ShouldReturnRich() {

        double todayRate = 90.0;
        double yesterdayRate = 90.0;

        mockOxrResponses(todayRate, yesterdayRate);

        String result = currencyComparisonService.getCurrencyTrendTag();

        assertEquals("rich", result);
    }



    private void mockOxrResponses(double todayRate, double yesterdayRate) {
        Map<String, Double> todayRates = new HashMap<>();
        todayRates.put(currency, todayRate);

        Map<String, Double> yesterdayRates = new HashMap<>();
        yesterdayRates.put(currency, yesterdayRate);

        OxrResponse todayResponse = new OxrResponse();
        todayResponse.setRates(todayRates);

        OxrResponse yesterdayResponse = new OxrResponse();
        yesterdayResponse.setRates(yesterdayRates);

        when(oxrClient.getLatestRates(anyString())).thenReturn(todayResponse);
        when(oxrClient.getHistoricalRates(anyString(), anyString())).thenReturn(yesterdayResponse);
    }

    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
