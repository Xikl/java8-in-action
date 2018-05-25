package com.ximo.java8.chap11;

import com.ximo.java8.chap11.future.AsyncUtil;

/**
 * @author 朱文赵
 * @date 2018/5/15 12:22
 * @description
 */
public class ExchangeService {

    /** money 枚举类 */
    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    /** 获得汇率 */
    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    /** 获得汇率 并添加一秒的延迟时间 */
    private static double getRateWithDelay(Money source, Money destination) {
        AsyncUtil.delay();
        return destination.rate / source.rate;
    }



}
