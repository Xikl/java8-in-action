package com.ximo.java8.chap4;

import com.ximo.java8.chap4.domain.Dish;

import static com.ximo.java8.chap4.domain.Dish.MENU;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/2/28 13:55
 * @description
 */
public class DishStream {

    /**
     * 获得前三个高热量的食物名字
     *
     * @param dishes 食物
     */
    private void getThreeHighCaloricDishNames(List<Dish> dishes) {
        List<String> threeHighCaloricDishNames = dishes.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
    }

    /**
     * 遍历食物
     *
     * @param dishes 食物
     */
    private static void foreachDish(List<Dish> dishes) {
        Stream<Dish> dishStream = dishes.stream();
        dishStream.forEach(System.out::print);
        System.out.println("-------------------------");
        //error 该流已经被关闭
        dishStream.forEach(System.out::print);
    }

    /**
     * 统计热量大于 300 的食物
     * @param dishes 食物
     * @return
     */
    private static long count(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
//                .limit(3)
                .count();

    }
    public static void main(String[] args) {
        System.out.println(count(MENU));
    }

}
