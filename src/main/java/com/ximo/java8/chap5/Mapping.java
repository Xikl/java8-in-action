package com.ximo.java8.chap5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/2/28 15:34
 * @description
 */
public class Mapping {

    /** 单词类名*/
    private static List<String> words = new ArrayList<>(Arrays.asList("words", "hello"));

    /**
     * error 不是想要的结果
     * @return
     */
    private static List<String[]> splitWordsAndDistinct() {
        return words.stream()
                .map(w -> w.split(""))
                .distinct()
                .collect(toList());

    }

    /**
     * error 不是想要的结果
     * @return
     */
    private static List<Stream<String>> splitWordsAndDistinct2() {
        return words.stream()
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
    }

    /**
     * 分离后 正确的去重 单个字符列表
     * @return 分离后 正确的去重 单个字符列表
     */
    private static List<String> splitWordAndDistinctRight() {
        return words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .collect(toList());
    }


    public static void main(String[] args) {
        splitWordAndDistinctRight().forEach(System.out::print);
    }



}
