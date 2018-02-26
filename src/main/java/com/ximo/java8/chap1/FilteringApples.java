package com.ximo.java8.chap1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 朱文赵
 * @date 2018/2/26 12:07
 * @description 过滤苹果
 */
public class FilteringApples {

    public static void main(String[] args) {

        /*数据源*/
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"), new Apple(120, "red"));

        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);

        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyAppple);
        System.out.println(heavyApples);

        List<Apple> greenApples2 = filterApples(inventory, a -> "green".equals(a.getColor()));
        System.out.println(greenApples2);

        List<Apple> heavyApples2 = filterApples(inventory, a -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        List<Apple> apples = filterApples(inventory, a -> a.getWeight() < 80 || "red".equals(a.getColor()));
        System.out.println(apples);

        List<Apple> heavyApples3 = inventory.stream().filter(a -> a.getWeight() > 150).collect(Collectors.toList());
        System.out.println(heavyApples3);


    }

    /**
     * 过滤绿色苹果
     * @param inventory 库存
     * @return 绿色苹果
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 挑选重量大于150的苹果
     * @param inventory 库存
     * @return 重量大于150的苹果
     */
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 是否是绿色苹果
     * @param apple 苹果
     * @return 绿色苹果
     */
    private static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    /**
     * 重量是大于150
     * @param apple 苹果
     * @return 大于150苹果
     */
    private static boolean isHeavyAppple(Apple apple) {
        return apple.getWeight() > 150;
    }

    /**
     * 过滤苹果 传递微词
     * @param inventory 苹果库存
     * @param predicate 谓词
     * @return 按条件过滤的苹果集合
     */
    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Apple {

        /** 重量*/
        private Integer weight;
        /** 颜色*/
        private String color;

    }
}
