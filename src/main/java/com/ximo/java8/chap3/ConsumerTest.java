package com.ximo.java8.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 朱文赵
 * @date 2018/2/27 10:59
 * @description
 */
public class ConsumerTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        foreach(list, System.out::print);
        System.out.println("\n原生Java8操作-----------");
        list.forEach(System.out::print);
    }

    /**
     * 对Consumer实现简单foreach
     * @param list 一个list 集合
     * @param consumer 无返回的accept 的实现 使用Lambda
     * @param <T> 泛型
     */
    private static <T> void foreach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }




}
