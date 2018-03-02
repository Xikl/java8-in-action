package com.ximo.java8.chap6;

import com.ximo.java8.chap6.domain.Transaction;
import com.ximo.java8.chap6.enums.Currency;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 朱文赵
 * @date 2018/3/2 9:37
 * @description 对交易的归约操作 用Collector
 */
public class GroupingTransactions {

    /** 初始化交易值 */
    public static List<Transaction> transactions = new ArrayList<>(Arrays.asList(
            new Transaction(Currency.EUR, new BigDecimal(1500)),
            new Transaction(Currency.USD, new BigDecimal(2300)),
            new Transaction(Currency.GBP, new BigDecimal(9900)),
            new Transaction(Currency.EUR, new BigDecimal(1100)),
            new Transaction(Currency.JPY, new BigDecimal(7800)),
            new Transaction(Currency.CHF, new BigDecimal(6700)),
            new Transaction(Currency.EUR, new BigDecimal(5600)),
            new Transaction(Currency.USD, new BigDecimal(4500)),
            new Transaction(Currency.CHF, new BigDecimal(3400)),
            new Transaction(Currency.GBP, new BigDecimal(3200)),
            new Transaction(Currency.USD, new BigDecimal(4600)),
            new Transaction(Currency.JPY, new BigDecimal(5700)),
            new Transaction(Currency.EUR, new BigDecimal(6800))));

    /**
     * 通过命令的形式来 对交易按货币种类进行分类
     */
    private static void groupImperatively() {
        //创建一个大小为16的交易Map 键为货币种类 值为交易类
        Map<Currency, List<Transaction>> transactionsByCurrenciesMap = new HashMap<>(16);
        //遍历已知交易类
        for (Transaction transaction : transactions) {
            //获得该交易的货币种类
            Currency currency = transaction.getCurrency();
            //通过货币种类获得该交易类型list
            List<Transaction> transactionsForCurrency = transactionsByCurrenciesMap.get(currency);
            //交易类型list为空
            if (transactionsForCurrency == null) {
                //新建一个ArrayList
                transactionsForCurrency = new ArrayList<>();
                //放入 交易Map中 键为 货币种类 值为该空的交易list
                transactionsByCurrenciesMap.put(currency, transactionsForCurrency);
            }
            //将这个交易类放入到该交易list中
            transactionsForCurrency.add(transaction);
        }
        //打印
        System.out.println(transactionsByCurrenciesMap);
    }

    /**
     * 通过流式操作来进行收集 并且打印出来
     */
    private static void groupFunctionallyAndPrint() {
        transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency)).forEach((k, v) -> System.out.println(k + ", " + v));
    }


    public static void main(String[] args) {
//        groupImperatively();
        groupFunctionallyAndPrint();
    }






}
