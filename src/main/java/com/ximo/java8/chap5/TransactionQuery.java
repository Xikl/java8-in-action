package com.ximo.java8.chap5;

import com.ximo.java8.chap5.domain.Trader;
import com.ximo.java8.chap5.domain.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/3/1 13:26
 * @description p98 中 关于交易查询的练习
 */
public class TransactionQuery {

    private static List<Transaction> transactions;
    static{
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        transactions = new ArrayList<>(Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        ));
    }

    /**
     * 找到所有的交易 并且排序
     * @return 所有的2011发生的交易，并且安装金额排序
     */
    private static List<Transaction> findAll2011TransAndSort() {
        return transactions.stream()
                .filter(t -> t.getYear().equals(2011))
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
    }

    /**
     * 查找交易员们都在那里工作过
     *
     * @return 交易员工作过的城市
     */
    private static List<String> findTradersWorkedCity() {
        return transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
    }

    /**
     * 查找剑桥工作的交易员 并排序
     * @return 剑桥工作的交易员按名字排序
     */
    private static List<Trader> findCambridgeTraderAndSort() {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
    }

    /**
     * 查找所有交易员的名字
     *
     * @return 所有交易员的名字
     */
    private static List<String> findTraderNameAndSorted() {
        return transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(toList());
    }

    /**
     * 获得所有的交易员的名字 并将其拼接起来
     * @return 所有名字连接起来的字符串
     */
    private static String getAllTraderNameSeqAndDistinct() {
        return transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
    }

    /**
     * 采用 Collectors中的Joining来时操作
     * @return joining中采用StringBuilder 的Append来实现 不用每次都创建一个String对象
     */
    private static String getAllTraderNameSeqAndDistinctByJoining() {
        return transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
    }

    /**
     * 有没有交易员工作在米兰
     * @return 是否有人工作在米兰
     */
    private static boolean isAnyTraderWorkingInMilan() {
        return transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
    }

    /**
     * 打印所有工作在剑桥的交易员的所有交易额
     */
    private static void printTraderTransValueOfWorkingInCambridge() {
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::print);
    }

    /**
     * 返回交易员的交易额的最大值
     * @return 交易员的交易额的最大值
     */
    private static Integer maxTransValue() {
        return transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElse(-1);
    }

    /**
     * 获得最小的交易
     * @return 最小的交易
     */
    private static Transaction minTrans() {
        return transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
                .orElse(null);
    }

}
