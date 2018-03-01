package com.ximo.java8.chap5.domain;

import lombok.Data;

/**
 * @author 朱文赵
 * @date 2018/3/1 13:28
 * @description 交易员
 */
@Data
public class Trader {

    /** 交易员名字*/
    private final String name;
    /** 交易员所在城市*/
    private final String city;

}
