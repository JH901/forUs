package com.easytravel.forus.feign.currency.response;

import com.easytravel.forus.domain.CurrencyInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CurrencyInformationResponse {
    private List<CurrencyInfo> currencyInfos;
}
