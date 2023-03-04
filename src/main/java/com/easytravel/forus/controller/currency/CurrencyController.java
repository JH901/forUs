package com.easytravel.forus.controller.currency;

import com.easytravel.forus.controller.currency.response.CurrencyInfoResponse;
import com.easytravel.forus.domain.CurrencyInfo;
import com.easytravel.forus.service.currency.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/v1/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<CurrencyInfoResponse> getCurrencyInfoList(){
        final String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        final List<CurrencyInfo> currencyInfoList = currencyService.getCurrencyInfoList();
        return new ResponseEntity<>(CurrencyInfoResponse.of(date, currencyInfoList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity setCurrencyInfo(){
        currencyService.setCurrencyInfo();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/value")
    public ResponseEntity<BigDecimal> getConversionValue(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount
    ) throws Exception {
        return new ResponseEntity<>(currencyService.getConversionValue(from, to, amount), HttpStatus.OK);
    }
}
