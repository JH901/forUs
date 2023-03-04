package com.easytravel.forus.feign.currency;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "currencyFeignClient", url = "${easy-travel.data.currency.url}", configuration = CurrencyFeignConfiguration.class)
public interface CurrencyFeignClient {

    @PostMapping
    List getCurrencyInfo(@RequestParam("authkey") String authKey, @RequestParam("searchdate")String date, @RequestParam("data") String dataType);
}
