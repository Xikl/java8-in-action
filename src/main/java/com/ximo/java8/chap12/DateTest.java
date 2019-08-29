package com.ximo.java8.chap12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author 朱文赵
 * @date 2019/8/29
 */
public class DateTest {

    public static void main(String[] args) {
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

        System.out.println(lastDay.getDayOfMonth());

        int dayOfMonth = LocalDate.of(2019, 2, 20)
                .with(TemporalAdjusters.lastDayOfMonth())
                .getDayOfMonth();

        System.out.println(dayOfMonth);

        LocalDate inputDate = LocalDate.parse("2019-02-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(inputDate);

    }
}
