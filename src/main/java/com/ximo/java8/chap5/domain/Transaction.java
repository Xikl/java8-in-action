package com.ximo.java8.chap5.domain;

import lombok.Data;

/**
 * @author 朱文赵
 * @date 2018/3/1 13:31
 * @description 交易 实体类
 */
@Data
public class Transaction {

    /** 该交易所属交易员*/
    private final Trader trader;
    /** 交易年份*/
    private final Integer year;
    /** 交易值*/
    private final Integer value;


}
