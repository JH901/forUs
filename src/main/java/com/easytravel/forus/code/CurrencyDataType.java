package com.easytravel.forus.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyDataType {
    AP01("AP01", "환율"),
    AP02("AP02", "대출금리"),
    AP03("AP03", "국제금리"),
    ;

    private String typeCd;
    private String desc;
}
