package com.ximo.java8.chap3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/2/27 14:56
 * @description 排序
 */
public class Sorting {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>(Arrays.asList(new Apple(80, "green"),
                new Apple(155, "red"), new Apple(120, "red")));
        apples.sort(new AppleCompartor());
        System.out.println(apples);

        //改变ArrayList第二个的值
        apples.set(1, new Apple(30, "green"));
        System.out.println(apples);

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }

        });

        apples.set(1, new Apple(20, "red"));
        apples.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Apple {
        private Integer weight;
        private String color;
    }

    static class AppleCompartor implements Comparator<Apple> {

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }

    }



}
