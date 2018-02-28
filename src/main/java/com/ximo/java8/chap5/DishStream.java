package com.ximo.java8.chap5;

import com.ximo.java8.chap4.domain.Dish;
import com.ximo.java8.chap4.enums.Type;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/2/28 15:07
 * @description
 */
public class DishStream {

    /**
     * 使用 Skip
     * @param dishes 食物
     * @return 跳过头两个的食物的List 且 热量 大于 300
     */
    public static List<Dish> skipHeaderDishes(List<Dish> dishes) {
        return dishes.stream().filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
    }

    /**
     * 挑选头两道荤菜
     * @param dishes 食物
     * @return 头两道荤菜
     */
    public static List<Dish> findHeaderMeat(List<Dish> dishes) {
        return dishes.stream().filter(d -> d.getType().equals(Type.MEAT))
                .limit(2)
                .collect(toList());
    }

    /**
     * 获得全部的Dish的名字
     * @param dishes 食物
     * @return 所以食物的名字list
     */
    public static List<String> getDishesName(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getName)
                .collect(toList());
    }

    /**
     * 获得每道菜名的长度 list
     * @param dishes 食物
     * @return 每道菜名的list
     */
    public static List<Integer> getDishesNameLength(List<Dish> dishes) {
        return dishes.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
    }

    /**
     * anyOrNoneOrAllMatch 短路求值
     * @param dishes 菜肴
     */
    private static void anyOrNoneOrAllMatch(List<Dish> dishes) {
        if (dishes.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("蔬菜爱好者可以点这个菜");
        }

        if (dishes.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("健康的菜谱");
        }

        if (dishes.stream().noneMatch(d -> d.getType().equals(Type.MEAT))) {
            System.out.println("这个菜谱没有肉");
        }
    }

    /**
     * 返回Dish的一个可以选择类 不用进行空指针检查
     * @param dishes 菜肴
     * @return
     */
    private static Optional<Dish> findAny(List<Dish> dishes) {
        return dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny();
    }
}
