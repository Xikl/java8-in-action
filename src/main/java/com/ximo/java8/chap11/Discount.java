package com.ximo.java8.chap11;

import static com.ximo.java8.chap11.future.AsyncUtil.delay;
import static com.ximo.java8.chap11.future.AsyncUtil.format;

/**
 * @author 朱文赵
 * @date 2018/5/3 8:55
 * @description 商品折扣类
 */
public class Discount {

    /** 内置对象 枚举类 折扣标准 */
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount() {
        return null;
    }

    /**
     * 计算折扣后的价格
     *
     * @param price 价格
     * @param code 折扣
     * @return 格式化 折扣后的价格
     */
    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

}
