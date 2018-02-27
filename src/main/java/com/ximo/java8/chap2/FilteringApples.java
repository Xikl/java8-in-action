package com.ximo.java8.chap2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/2/26 13:40
 * @description
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"), new Apple(120, "red"));

        List<Apple> red = filterAppleByColor(inventory, "red");
        System.out.println(red);

        List<Apple> heavyApple = filterAppleByWeight(inventory, 150);
        System.out.println(heavyApple);

        List<Apple> apples = filter(inventory, new AppleColorPredicate());
        System.out.println(apples);

        filter(inventory, new AppleWeightPredicate());

        filter(inventory, new AppleRedAndHeavyPredicate());

        /* 次数相当于 predicate 中 test的方法的实现  apple -> "red".equals(apple.getColor()*/
        filter(inventory, apple -> "red".equals(apple.getColor()));

    }

    /**
     * 过滤苹果
     * @param inventory 库存
     * @param predicate 谓词
     * @return 苹果
     */
    private static List<Apple> filter(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 通过重量判断
     * @param inventory 库存
     * @param weight 重量
     * @return 苹果
     */
    private static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        return filterAppleByColor(inventory, "green");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Apple {
        /** 重量*/
        private Integer weight;
        /** 颜色*/
        private String color;
    }

    interface ApplePredicate {

        /**
         * 判断apple的方法
         * @param apple 苹果
         * @return true or false 是否满足条件
         */
        boolean test(Apple apple);
    }

    static class AppleWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleColorPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }
}
