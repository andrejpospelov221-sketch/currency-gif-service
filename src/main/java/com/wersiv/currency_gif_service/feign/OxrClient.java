package com.wersiv.currency_gif_service.feign;


import com.wersiv.currency_gif_service.response.OxrResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "oxr-client", url = "${oxr.url}")
public interface OxrClient {


    @GetMapping("/latest.json")
    OxrResponse getLatestRates(@RequestParam("app_id") String appId);


    @GetMapping("/historical/{date}.json")
    OxrResponse getHistoricalRates(@PathVariable("date") String date, @RequestParam("app_id") String appId);
}
