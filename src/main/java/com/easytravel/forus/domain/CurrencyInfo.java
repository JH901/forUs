package com.easytravel.forus.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Data
@RedisHash("currency")
public class CurrencyInfo {

    @Id
    private String cur_unit; // 통화코드
    private String cur_nm; // 통화명
    private BigDecimal kftc_bkpr; // 서울외국환중개 장부가격 (원화 금액)
}
