package com.ximo.java8.chap5;

import com.ximo.java8.chap4.domain.Dish;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 朱文赵
 * @date 2018/3/1 14:38
 * @description IntStream 、 DoubleStream 和
 * LongStream ，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本。
 */
public class MapToNumericStreams {

    /**
     * 使用mapToInt 将Stream转化为特殊的IntStream
     *
     * @param dishes 菜肴 {@link Dish}
     * @return 所有的菜肴热量求和
     */
    private static int sum(List<Dish> dishes) {
        return dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }

    /**
     * 将数值流 IntStream转化为Stream
     * @param dishes 菜肴 {@link Dish}
     * @return 一般流
     */
    private static Stream<Integer> boxedStream(List<Dish> dishes) {
        return dishes.stream()
                .mapToInt(Dish::getCalories)
                .boxed();
    }

    /**
     * 不用mapToInt的话就要用你reduce(Integer::max)的形式
     * {@code return transactions.stream()
     *          .map(Transaction::getValue)
     *          .reduce(Integer::max)
     *          .orElse(-1);}
     *
     * @param dishes 菜肴
     * @return 菜肴的卡路里的最大值
     */
    private static Integer maxCalories(List<Dish> dishes) {
        return dishes.stream()
                .mapToInt(Dish::getCalories)
                .max()
                .orElse(-1);
    }

    /**
     * 通过rangeCosed 设置值的边界
     * @return 1 到100 中的偶数 的个数  [1, 100]
     * 返回五十个 因为包含结束值
     */
    private static long rangeColsed() {
        return IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0)
                .count();
    }

    /**
     * 注释同上
     * 不同的是 这里只返回49个 [1, 100)
     *
     * @return 49个偶数
     */
    private static long range() {
        return IntStream.range(1, 100)
                .filter(i -> i % 2 == 0)
                .count();
    }

    private static Stream<int[]> getPythagoreanTriple() {
        return IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
    }

    /**
     * 获得勾股数 第二种方法
     * @return 勾股数
     */
    private static Stream<double[]> getPythagoreanTriple2() {
        return IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                .filter(t -> t[2] % 1 == 0);
    }

    public static void main(String[] args) {
        getPythagoreanTriple2().limit(5).forEach( t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }


}
