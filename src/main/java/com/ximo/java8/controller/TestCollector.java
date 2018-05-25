package com.ximo.java8.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/5/11 15:51
 * @description
 */
public class TestCollector {

    public static void main(String[] args) {
        List<Example> list = new ArrayList<>();
        list.add(new Example("name1", "localhost", 8080));
        list.add(new Example("name2", "localhost", 8080));
        list.add(new Example("name3", "something", 8080));
        list.add(new Example("name4", "localhost", 1000));

        long start = System.nanoTime();
        List<Example> collect = removeSameHostAndPort(list);
        System.out.println(collect);
        System.out.println("耗时: " + (System.nanoTime() - start) / 1_000_000  );
    }

    /** 去掉 一样的 */
    private static List<Example> removeSameHostAndPort(List<Example> list) {
        return list.stream()
                    .flatMap(a -> list.stream().filter(isNotSame(a)))
                    .collect(toList());
    }

    /** 获得不一样的对象*/
    private static Predicate<Example> isNotSame(Example a) {
        return b -> !a.getHost().equals(b.getHost()) && !a.getPort().equals(b.getPort());
    }

    private static Example distinctSameHostAndPort(List<Example> list) {
//        list.stream()
//                .reduce(new ArrayList<Example>(), (exampleArrayList, example) -> {
//                    exampleArrayList.remove(example);
//                    return exampleArrayList;
//                }, (exampleArrayList, exampleArrayList2) -> {});
        return null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Example implements Serializable {

        private static final long serialVersionUID = 8197777260503094604L;

        /**
         * 采集点名称
         */
        private String name;

        /**
         * 采集主机
         */
        private String host;

        /**
         * 端口
         */
        private Integer port;

    }

}
