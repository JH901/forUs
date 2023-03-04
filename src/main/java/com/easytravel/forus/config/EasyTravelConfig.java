package com.easytravel.forus.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class EasyTravelConfig {

    CurrencyConfig currencyConfig;

    @Getter
    @Component
    public class CurrencyConfig{
        @Value("${easy-travel.data.currency.url}")
        String url;

        @Value("${easy-travel.data.currency.api-key}")
        String apiKey;
    }
}
