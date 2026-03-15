package com.wersiv.currency_gif_service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;



public class OxrResponse {

    @JsonProperty("disclaimer")
    private String disclaimer;

    @JsonProperty("license")
    private String license;

    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonProperty("base")
    private String base;

    @Getter
    @Setter
    @JsonProperty("rates")
    private Map<String, Double> rates;

}
