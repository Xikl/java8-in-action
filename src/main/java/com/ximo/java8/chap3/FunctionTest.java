package com.ximo.java8.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author 朱文赵
 * @date 2018/2/27 11:07
 * @description 测试Function的apply方法
 */
public class FunctionTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("lambda", "java", "python");
        map(list, String::length).forEach(System.out::print);
        System.out.println("\n原生Java8操作-----------");
        list.stream().map(String::length).forEach(System.out::print);

    }

    /**
     * 映射方法 将 T 映射为R
     * @param list
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

}
