package com.easytravel.forus.controller.currency.response;

import com.easytravel.forus.domain.CurrencyInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CurrencyInfoResponse {
    private String standardDate;
    private List<CurrencyInfo> currencyInfoList;

    public static CurrencyInfoResponse of(String standardDate, List<CurrencyInfo> currencyInfoList){
        return new CurrencyInfoResponse(standardDate, currencyInfoList);
    }
}
