package com.wersiv.currency_gif_service.controller;


import com.wersiv.currency_gif_service.CurrencyComparisonService;
import com.wersiv.currency_gif_service.feign.GiphyClient;
import com.wersiv.currency_gif_service.response.GiphyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gif")
public class GiphyController {

    @Value("${giphy.api_key}")
    private String giphyApiKey;

    private final GiphyClient giphyClient;

    private final CurrencyComparisonService currencyComparisonService;

    public GiphyController(GiphyClient giphyClient, CurrencyComparisonService currencyComparisonService) {
        this.giphyClient = giphyClient;
        this.currencyComparisonService = currencyComparisonService;
    }



    @GetMapping("/random")
    public GiphyResponse getRandomGif() {

      return giphyClient.getRandomGif(giphyApiKey, currencyComparisonService.getCurrencyTrendTag());

    }

}
