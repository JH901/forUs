package com.easytravel.forus.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@Data
@RedisHash("currency")
public class CurrencyInfo {

    private String result;

    @Id
    private String cur_unit;        // 통화코드

    private String cur_nm;          // 통화명
    private String ttb;             // 전신환(송금) 받으실때
    private String tts;             // 전신환(송금) 보내실때
    private String deal_bas_r;      // 매매 기준율
    private String bkpr;            // 장부가격
    private String yy_efee_r;       // 년환가료율
    private String ten_dd_efee_r;   //10일환가료율
    private String kftc_deal_bas_r; //서울외국환중개 매매기준율
    private String kftc_bkpr;   // 서울외국환중개 장부가격 (원화 금액)

    public BigDecimal krwValue(){
        Long value = Long.valueOf(kftc_bkpr.replaceAll(",",""));
        return BigDecimal.valueOf(value);
    }
}
