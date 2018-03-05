package com.ximo.java8.chap6;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author 朱文赵
 * @date 2018/3/5 16:05
 * @description
 */
public class Prime {

    /**
     * 判断是否是是 质数
     * 产生2 到 candidate的自然数， 然后依次将candidate 除以 i
     *
     * @param candidate 被除数
     * @return 是否是质数
     */
    private static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    /**
     * 判断是否是质数 将candidate 除以 2 sqrt(candidate)的数字
     *
     * @param candidate 被除数
     * @return 是否是质数
     */
    private static boolean isPrimeFinal(int candidate) {
        return IntStream.range(2, (int) Math.sqrt(candidate))
                .noneMatch(i -> candidate % i == 0);
    }

    /**
     * 注意 这里只是该方法是静态的 否则方法 partitioningBy() 中的方法可以不是静态的
     * <p>
     * <p>
     * 区分（2, range）是不是质数 然后按是不是质数分区
     *
     * @param range 边界
     * @return 按是不是质数 分区 返回Map
     */
    @BenchmarkMode(Mode.AverageTime)
    public static Map<Boolean, List<Integer>> partitionedByPrime(int range) {
        return IntStream.range(2, range)
                .boxed()
                .collect(partitioningBy(Prime::isPrimeFinal));
    }

    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    /**
     * 判断是不是质数 只用数字小于该数字的平方根 除以
     * @param primes 数字列表
     * @param candidate 被判断的数字
     * @return 是不是数字
     */
    private static boolean isPrimeFinal(List<Integer> primes, Integer candidate) {
        return takeWhile(primes, i -> i <= Math.sqrt(candidate))
                .stream().noneMatch(p -> candidate % p == 0);
    }

    /**
     * 当不满足predicate的test的方法的时候 返回截取之后的list（0， i）
     *
     * @param list 原list
     * @param predicate 谓词 条件
     * @param <A> 泛型
     * @return 截取后的list
     */
    private static <A> List<A> takeWhile(List<A> list, Predicate<A> predicate) {
        int i = 0;
        for (A a : list) {
            if (!predicate.test(a)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

}
