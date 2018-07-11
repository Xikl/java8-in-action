package com.ximo.java8.chap12;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

/**
 * @author 朱文赵
 * @date 2018/7/11 15:24
 * @description
 */
public class LocalDateExmaple {

    private static void oneLocateDate() {
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int monthValue = month.getValue();
        int dayOfMonth = localDate.getDayOfMonth();

        //同样可以获取字段
        LocalDate nowDate = LocalDate.now();
        nowDate.get(ChronoField.YEAR);
        nowDate.get(ChronoField.MONTH_OF_YEAR);
        nowDate.get(ChronoField.DAY_OF_MONTH);

        //----------------------------------
        //时间
        LocalTime localTime = LocalTime.now();
        int hour = localTime.getHour();
        int minute = localTime.getMinute();

    }

    public static void main(String[] args) {
    }

}
