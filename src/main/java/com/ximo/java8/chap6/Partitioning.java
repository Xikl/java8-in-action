package com.ximo.java8.chap6;

import com.ximo.java8.chap4.domain.Dish;
import com.ximo.java8.chap4.enums.Type;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * @author 朱文赵
 * @date 2018/3/2 17:01
 * @description 分区
 */
public class Partitioning {

    public static void main(String[] args) {
//        partitioningDishes(Dish.MENU).forEach((k, v) -> System.out.println(k + ", " + v));
//        //获得是素菜的Dish
//        partitioningDishes(Dish.MENU).get(true);

//        System.out.println(partitionDishesAndGroupBy(Dish.MENU));
//        test(Dish.MENU).forEach((k, v) -> System.out.println(k + ", " + v));
    }

    /**
     * 对是不是素菜进行分区
     * false, [Dish{name='pork', vegetarian=false, calories=800, type=MEAT},
     * Dish{name='beef', vegetarian=false, calories=700, type=MEAT},
     * Dish{name='chicken', vegetarian=false, calories=400, type=MEAT},
     * Dish{name='prawns', vegetarian=false, calories=400, type=FISH},
     * Dish{name='salmon', vegetarian=false, calories=450, type=FISH}]
     * true, [Dish{name='french fries', vegetarian=true, calories=530, type=OTHER},
     * Dish{name='rice', vegetarian=true, calories=350, type=OTHER},
     * Dish{name='season fruit', vegetarian=true, calories=120, type=OTHER},
     * Dish{name='pizza', vegetarian=true, calories=550, type=OTHER}]
     *
     * @param dishes 菜肴
     * @return 分区之后的素菜
     */
    private static Map<Boolean, List<Dish>> partitioningDishes(List<Dish> dishes) {
        return dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
    }

    /**
     * 通过filter来过滤是素菜的Dish
     *
     * @param dishes 菜肴
     * @return 为素菜的Dish列表
     */
    private static List<Dish> partitioningDishesByFilter(List<Dish> dishes) {
        return dishes.stream()
                .filter(Dish::isVegetarian).collect(toList());
    }

    /**
     * 对Dishes中的进行分区 然后对每个分区中的进行分组
     *
     * @param dishes 菜肴
     * @return 分区后分组
     */
    private static Map<Boolean, Map<Type, List<Dish>>> partitionDishesAndGroupBy(List<Dish> dishes) {
        return dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    /**
     * 找到素食和非素食中热量最高的菜
     *
     * @param dishes 菜肴
     * @return 找到素食和非素食中热量最高的菜
     */
    private static Map<Boolean, Dish> mostCaloricPartitionedByVegetarian(List<Dish> dishes) {
        return dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }

    /**
     * 回顾partitionedBy的用法
     * partitioned() 中必须使用谓词 即返回一个boolean的值
     *
     * @param dishes 菜肴
     */
    private static void partitionedByTest(List<Dish> dishes) {
        //{ false={false=[chicken, prawns, salmon], true=[pork, beef]},
        //true={false=[rice, season fruit], true=[french fries, pizza]}}
        Map<Boolean, Map<Boolean, List<Dish>>> collect1 = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));

        // {false=5, true=4}
        Map<Boolean, Long> collect = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian, counting()));

    }





}
