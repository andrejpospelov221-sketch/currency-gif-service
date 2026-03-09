package com.wersiv.currency_gif_service.feign;


import com.wersiv.currency_gif_service.response.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy-client", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("/random")
    GiphyResponse getRandomGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);

}
