package com.ximo.java8.chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 朱文赵
 * @date 2018/2/26 17:02
 * @description 初始Predicate
 */
public class PredicateTest {

    /**
     * 结合Predicate来进行自己的过滤
     * @param list list
     * @param predicate 谓词
     * @param <T> 泛型
     * @return 过滤之后的list
     */
    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("string1");
        list.add("string2");
        list.add("");
        List<String> filterString = filter(list, s -> s != null && s.length() > 0);
        filterString.forEach(System.out::print);

        System.out.println("\n使用java8 流式操作-----");

        //使用java8 流式操作
        List<String> filterString2 = list.stream().filter(s -> s != null && s.length() > 0).collect(Collectors.toList());
        filterString2.forEach(System.out::print);

    }




}
