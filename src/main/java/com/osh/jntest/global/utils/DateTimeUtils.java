package com.osh.jntest.global.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private DateTimeUtils() {
        throw new AssertionError("Can't use a constructor with utility class.");
    }

    /**
     * 현재 년도를 문자열로 반환.
     * ex. 2020년 --> "2020"
     *
     * @return
     */
    public static String getCurrentYear() {
        LocalDate currentDate = LocalDate.now();
        return Integer.toString(currentDate.getYear());

    }

    /**
     * 현재시간을 문자열로 반환.
     * ex. 2022-08-09 00:00:00
     *
     * @return
     */
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static boolean  isMatchDate(String date, String pattern){
        try{
            SimpleDateFormat dateFormat = new  SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return  true;
        }catch (ParseException  e){
            return  false;
        }
    }
}
