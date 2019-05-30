package com.ximo.java8.chap12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;

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

    public static void main(String[] args) throws InterruptedException {
        // 稀奇古怪的时间格式
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse("2019-04-22T13:28:17.093Z", df);
        System.out.println(localDateTime);

        System.out.println(Instant.now());

        LocalDateTime start = LocalDateTime.now();
        TimeUnit.SECONDS.sleep(2);

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println(start);
        System.out.println(end);
        System.out.println(duration.toMillis());

        final long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(l);

        System.out.println(Instant.now().atOffset(ZoneOffset.of("+8")));

    }

}
