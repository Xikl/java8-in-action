package com.ximo.java8.chap11.future;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

/**
 * @author 朱文赵
 * @date 2018/4/27 12:19
 * @description
 */
public class AsyncUtil {

    /** 格式化 具体可以百度 ##.## 和 00.00 的区别*/
    private static final DecimalFormat FORMATTER = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.CHINA));

    private static final Random RANDOM = new Random(0);


    /**
     * 模拟延迟 通常为查询数据库等操作
     */
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /** 随机延迟 */
    public static void randomDelay() {
        int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 格式化浮点数
     *
     * @param number 价格
     * @return 格式化后的浮点数
     */
    public static double format(double number) {
        synchronized (FORMATTER) {
            return new Double(FORMATTER.format(number));
        }
    }

}
