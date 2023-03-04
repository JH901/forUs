package com.easytravel.forus.service.currency;

import com.easytravel.forus.code.CurrencyDataType;
import com.easytravel.forus.config.EasyTravelConfig;
import com.easytravel.forus.domain.CurrencyInfo;
import com.easytravel.forus.feign.currency.CurrencyFeignClient;
import com.easytravel.forus.repository.CurrencyRepository;
import com.easytravel.forus.util.DateTimeUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final EasyTravelConfig.CurrencyConfig currencyConfig;

    private final CurrencyFeignClient currencyFeignClient;

    private final CurrencyRepository currencyRepository;

    public List<CurrencyInfo> getCurrencyInfoList() {
        return currencyRepository.findAll();
    }

    /**
     * 환율 정보 조회
     * */
    public BigDecimal getConversionValue(String from, String to, BigDecimal amount) throws Exception {
        return getCurrencyInfo(from, to).multiply(amount);
    }

    private BigDecimal getCurrencyInfo(String from, String to) throws Exception {
        final CurrencyInfo fromInfo = currencyRepository.findById(from)
                .orElseThrow(() -> new Exception(from + "은/는 지원하지 않는 통화입니다."));
        final CurrencyInfo toInfo = currencyRepository.findById(to)
                .orElseThrow(() -> new Exception(to + "은/는 지원하지 않는 통화입니다."));

        return fromInfo.krwValue().divide(toInfo.krwValue(), 5, RoundingMode.HALF_UP);
    }

    public void setCurrencyInfo() {
        final LocalDate now = DateTimeUtil.getKoreaLocalDate();
        if (!DateTimeUtil.isWeekend(now)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            final String date = DateTimeUtil.getDateByFormat(now, "yyyymmdd");
            List<CurrencyInfo> result = new ArrayList<>();
            List currencyInfo = currencyFeignClient.getCurrencyInfo(currencyConfig.getApiKey(), date, CurrencyDataType.AP01.getTypeCd());
            currencyInfo.forEach(info -> result.add(objectMapper.convertValue(info, CurrencyInfo.class)));

            currencyRepository.saveAll(result);
        }
    }
}
