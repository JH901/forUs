package com.easytravel.forus.feign.currency.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CurrencyInformationResponse {
    private boolean success;
    private String timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}
