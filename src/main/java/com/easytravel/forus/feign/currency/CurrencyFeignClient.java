package com.easytravel.forus.feign.currency;

import com.easytravel.forus.feign.currency.response.CurrencyInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "currencyFeignClient", url = "${easy-travel.data.currency.url}", configuration = CurrencyFeignConfiguration.class)
public interface CurrencyFeignClient {

    @GetMapping("/latest")
    CurrencyInformationResponse getCurrencyInfo(@RequestHeader("apikey") String authKey, @RequestParam("base")String base);
}
