package com.easytravel.forus.service.currency;

import com.easytravel.forus.domain.CurrencyInfo;
import com.easytravel.forus.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public List<CurrencyInfo> getCurrencyInfoList() {
        return currencyRepository.findAll();
    }

    public BigDecimal getCurrencyInfo(String from, String to) throws Exception {
        final CurrencyInfo fromInfo = currencyRepository.findById(from)
                .orElseThrow(()-> new Exception(from + "은/는 지원하지 않는 통화입니다."));
        final CurrencyInfo toInfo = currencyRepository.findById(to)
                .orElseThrow(()-> new Exception(to + "은/는 지원하지 않는 통화입니다."));

        final BigDecimal fromValueByKrw = fromInfo.getKftc_bkpr();
        final BigDecimal toValueByKrw = toInfo.getKftc_bkpr();

        return fromValueByKrw.divide(toValueByKrw, 5, RoundingMode.HALF_UP);
    }

    public void setCurrencyInfo(){

    }
}
