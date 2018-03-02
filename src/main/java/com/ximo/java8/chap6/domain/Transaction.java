package com.ximo.java8.chap6.domain;

import com.ximo.java8.chap6.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 朱文赵
 * @date 2018/3/2 9:35
 * @description
 */
@Data
public class Transaction {

    /** 货币种类*/
    private final Currency currency;
    /** 交易金额*/
    private final BigDecimal value;

}
