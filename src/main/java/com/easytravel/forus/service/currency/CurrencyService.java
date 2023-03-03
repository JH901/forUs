package com.easytravel.forus.service.currency;

import com.easytravel.forus.controller.currency.response.CurrencyInfoResponse;
import com.easytravel.forus.domain.CurrencyInfo;
import com.easytravel.forus.feign.currency.CurrencyFeignClient;
import com.easytravel.forus.feign.currency.response.CurrencyInformationResponse;
import com.easytravel.forus.repository.CurrencyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyFeignClient currencyFeignClient;

    private final CurrencyRepository currencyRepository;

    public List<CurrencyInfo> getCurrencyInfoList() {
        return currencyRepository.findAll();
    }

    public BigDecimal getCurrencyInfo(String from, String to) throws Exception {
        final CurrencyInfo fromInfo = currencyRepository.findById(from)
                .orElseThrow(()-> new Exception(from + "은/는 지원하지 않는 통화입니다."));
        final CurrencyInfo toInfo = currencyRepository.findById(to)
                .orElseThrow(()-> new Exception(to + "은/는 지원하지 않는 통화입니다."));

        final BigDecimal fromValueByKrw = fromInfo.krwValue();
        final BigDecimal toValueByKrw = toInfo.krwValue();

        return fromValueByKrw.divide(toValueByKrw, 5, RoundingMode.HALF_UP);
    }

    public void setCurrencyInfo(){
        final String authKey = "EZ361j4Z62giStp4zAvGM7R4ABShWhh9";
        final String dataType = "AP01";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<CurrencyInfo> result = new ArrayList<>();
        List currencyInfo = currencyFeignClient.getCurrencyInfo(authKey, "20230303", dataType);
        currencyInfo.stream().forEach(info -> {
            result.add(objectMapper.convertValue(info, CurrencyInfo.class));
        });
        currencyRepository.saveAll(result);
        log.info("{}",currencyInfo);
    }

    public List test(List list){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        try {
////            CurrencyInfoResponse response = objectMapper.readValue(complicatedJson, PersonDto.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
