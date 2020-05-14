package com.ximo.java8;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author xikl
 * @date 2020/5/14
 */
public class DateTest {

    @Test
    public void testDay() {
        // 天
        final LocalDate dispatchDateDay = LocalDate.parse("2019-05-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final LocalDate localDate = dispatchDateDay.minusDays(1);
        System.out.println(localDate);


        // 单次
    }


    @Test
    public void testWeek() {
        // 周 首次调度时间
        final LocalDate dispatchDate = LocalDate.parse("2020-05-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // 周五 FRIDAY
        final DayOfWeek dispatchDateDayOfWeek = dispatchDate.getDayOfWeek();

        final LocalDate runningDate = LocalDate.parse("2020-05-15", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final LocalDate previousDispatchDate = runningDate.with(TemporalAdjusters.previous(dispatchDateDayOfWeek));
        // 2020-05-08
        System.out.println(previousDispatchDate);
    }

    @Test
    public void testMonth() {
        // 月
        final LocalDate dispatchDate = LocalDate.parse("2020-05-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        final LocalDate runningDate = LocalDate.parse("2020-07-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        final int dayOfMonth = dispatchDate.getDayOfMonth();
        final LocalDate previousDispatchDate = runningDate.minusMonths(1).withDayOfMonth(dayOfMonth);
        // 期望 六月二号
        System.out.println(previousDispatchDate);


    }
}
