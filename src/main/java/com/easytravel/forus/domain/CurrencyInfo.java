package com.easytravel.forus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Data
@RedisHash("currency")
@AllArgsConstructor
public class CurrencyInfo {
    @Id
    private String symbol;

    private BigDecimal value;

    public static CurrencyInfo of(String symbol, BigDecimal value){
        return new CurrencyInfo(symbol, value);
    }
}
