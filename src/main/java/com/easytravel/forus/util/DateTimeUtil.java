package com.easytravel.forus.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static LocalDate getKoreaLocalDate(){
        return LocalDate.now(ZoneId.of("Asia/Seoul"));
    }

    public static String getDateByFormat(LocalDate date, String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return date.format(dateTimeFormatter);
    }
    
    public static boolean isWeekend(LocalDate date){
        return date.getDayOfWeek().getValue() > 5;
    }
}
