package com.ximo.java8.chap11.future;

import com.ximo.java8.chap11.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 朱文赵
 * @date 2018/5/4 8:53
 * @description 价格折扣类.
 */
@Getter
@AllArgsConstructor
public class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    /**
     * 解析字符串
     *
     * @param s 包含商品名称 价格 折扣的字符串
     * @return 折扣对象
     */
    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

}
