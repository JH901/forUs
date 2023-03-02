package com.easytravel.forus.controller.currency;

import com.easytravel.forus.controller.currency.response.CurrencyInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyControllerTest {
    @Autowired
    CurrencyController currencyController;

    private static List<String> 환율정보리스트_예상;

    @BeforeEach
    void setUp() {
        환율정보리스트_예상 = Arrays.asList("1000","2000");
    }

//    @Test
     void 환율정보조회() {

        CurrencyInfoResponse 환율정보리스트 = currencyController.getCurrencyInfoList().getBody();

        assertAll(
                ()-> assertEquals(환율정보리스트_예상.size(), 환율정보리스트.getCurrencyInfoList().size()),
                ()-> assertTrue(환율정보리스트.getCurrencyInfoList().containsAll(환율정보리스트_예상)),
                ()-> assertTrue(환율정보리스트_예상.containsAll(환율정보리스트.getCurrencyInfoList()))
        );
    }

    @Test
    void 환율환산가() throws Exception {
        currencyController.getCurrencyInfo("a","a");
    }
}