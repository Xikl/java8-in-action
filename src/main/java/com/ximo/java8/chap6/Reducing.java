package com.ximo.java8.chap6;

import com.ximo.java8.chap4.domain.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summarizingInt;

/**
 * @author 朱文赵
 * @date 2018/3/2 14:41
 * @description
 */
public class Reducing {


    /**
     * 利用流来统计 大小
     *
     * @param dishes 菜肴
     * @return 菜肴的大小
     */
    private static long count(List<Dish> dishes) {
        return dishes.stream()
                .count();
    }

    /**
     * 求最大值
     *
     * @param dishes 菜肴
     * @return 卡路里最大的菜肴
     */
    private static Dish max(List<Dish> dishes) {
        Optional<Dish> collect = dishes.stream().max(Comparator.comparingInt(Dish::getCalories));
        if (!collect.isPresent()) {
            throw new RuntimeException("最大值不存在");
        }
        return collect.get();
    }

    /**
     * 对菜肴里的卡路里进行求和
     *
     * @param dishes 菜肴
     * @return 所有菜肴里的卡路里进行求和
     */
    private static int sum(List<Dish> dishes) {
        return dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }

    /**
     * 对菜肴里的卡路里进行 求平均数
     *
     * @param dishes 菜肴
     * @return 全部菜肴里的卡路里的平均数
     */
    private static double avg(List<Dish> dishes) {
        return dishes.stream()
                .mapToDouble(Dish::getCalories)
                .average()
                .orElse(-1);
    }

    /**
     * 包含所有的操作 sum avg min max count
     * 具体请查看{@link IntSummaryStatistics} 里面有详细的定义
     *
     * @param dishes 菜肴
     * @return sum avg min max count 五个值
     */
    private static IntSummaryStatistics commonOption(List<Dish> dishes) {
        return dishes.stream()
                .collect(summarizingInt(Dish::getCalories));
    }

    /**
     * 打印菜肴名字
     *
     * @param dishes 菜肴
     * @return 菜肴名字
     */
    private static String printDishesName(List<Dish> dishes) {
        return dishes.stream().
                map(Dish::getName)
                .collect(joining(", "));
    }

    /**
     * sum的底层实现 是通过reduce来实现的
     *
     * @param dishes 菜肴
     * @return 底层实现的sum机制
     */
    private static int bottomSum(List<Dish> dishes) {
        return dishes.stream().map(Dish::getCalories).reduce(0, Integer::sum);
//        return dishes.stream().map(Dish::getCalories).reduce(0, (a, b) -> a + b);
    }

    /**
     * 底层操作 Max 采用reduce 实现
     *
     * @param dishes 菜肴
     * @return 返回一个可选择的对象
     */
    private static Optional<Dish> bottomMax(List<Dish> dishes) {
        return dishes.stream().reduce((a, b) -> a.getCalories() > b.getCalories() ? a : b);
    }

    /**
     * 各种reducing 拼接字符串
     *
     * @param dishes 菜肴
     */
    private static void testReducing(List<Dish> dishes) {
        //最原始的方法进行 字符串相加
        String string1 = dishes.stream().map(Dish::getName).reduce((s1, s2) -> s1 + s2).orElse("");

//        dishes.stream().reduce((d1, d2) -> d1.getName() + d2.getName()).get();

        // 提供初始值 将字符串相加
        String string2 = dishes.stream().map(Dish::getName).reduce("", (s1, s2) -> s1 + s2);
        //使用joining进行字符串拼接 分隔符采用 ", "
        String string3 = dishes.stream().map(Dish::getName).collect(joining(", "));
    }


}
