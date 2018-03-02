package com.ximo.java8.chap6;

import com.ximo.java8.chap4.domain.Dish;
import com.ximo.java8.chap4.enums.Type;
import com.ximo.java8.chap6.enums.CaloricLevel;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author 朱文赵
 * @date 2018/3/2 13:08
 * @description
 */
public class Grouping {


    /**
     * 分组方法 将菜肴根据type来进行分组
     *
     * @param dishes 菜肴
     * @return 据type来进行分组的菜肴
     */
    private static Map<Type, List<Dish>> group(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType));
    }

    /**
     * 对卡路里的级别进行分组
     *
     * @param dishes 菜肴
     * @return 分组后的菜肴Map
     */
    private static Map<CaloricLevel, List<Dish>> groupByCaloricLevel(List<Dish> dishes) {

//        return dishes.stream()
////                .collect(groupingBy(dish ->
////                {
////                    if (dish.getCalories() <= 400) {
////                        return CaloricLevel.DIET;
////                    } else if (dish.getCalories() <= 700) {
////                        return CaloricLevel.NORMAL;
////                    } else {
////                        return CaloricLevel.FAT;}}
////                ));
        return dishes.stream()
                .collect(groupingBy(Dish::getCaloricLevel));
    }

    /**
     * 多级分组 真的很强
     * <p>
     * {MEAT={DIET=[chicken], NORMAL=[beef], FAT=[pork]},
     * FISH={DIET=[prawns], NORMAL=[salmon]},
     * OTHER={DIET=[rice, seasonal fruit], NORMAL=[french fries, pizza]}}
     *
     * @param dishes 菜肴
     * @return 多级分组菜肴
     */
    private static Map<Type, Map<CaloricLevel, List<Dish>>> mutilGroup(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType, groupingBy(Dish::getCaloricLevel)));
    }

    /**
     * 收集最大的卡路里 并且根据Type分类
     *
     * @param dishes 菜肴
     * @return 最大的卡路里 并且根据Type分类
     */
    private static Map<Type, Optional<Dish>> mostCaloricByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    /**
     * 根据Type 分类 然后收集dishes中卡路里最大的
     *
     * @param dishes 菜肴
     * @return 根据type分类后 然后收集dishes中的卡鲁里的最大值 作为value
     */
    private static Map<Type, Dish> mostDishByCaloricType(List<Dish> dishes) {
        return dishes.stream().collect(Collectors.toMap(Dish::getType, Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
//        // 对Calories进行求最大值，然后 用Optional.get()方法获得
//        return dishes.stream()
//                .collect(groupingBy(Dish::getType,
//                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }

    /**
     * 根据Type进行分组 然后对每个分组的卡路里进行求和
     *
     * @param dishes 菜肴
     * @return 根据Type进行分组后 对每个分组的卡路里进行求和的Map
     */
    private static Map<Type, Integer> sumDishCaloricByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    /**
     * 对dish 根据Type进行分组 然后存入 卡路里的级别
     * 注意这里用的是toSet 和 toList类似 只不过这里为了方便存入不同的值
     *
     * @param dishes 菜肴
     * @return 根据Type进行分组 然后存入 卡路里的级别的Map
     */
    private static Map<Type, Set<CaloricLevel>> groupDishByType(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getCaloricLevel, toSet())));
    }

    private static Map<Type, Set<CaloricLevel>> groupDishByTypeBoxedHashSet(List<Dish> dishes) {
        return dishes.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getCaloricLevel, toCollection(HashSet::new))));
    }}
