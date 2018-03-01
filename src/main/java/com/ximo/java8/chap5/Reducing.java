package com.ximo.java8.chap5;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 朱文赵
 * @date 2018/3/1 10:23
 * @description
 */
public class Reducing {

    /** 初始化数据*/
    private static List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    /**
     * 采用reduce 归约
     * 并使用lambda 和 方法引用进行 求和
     */
    private static void sum() {
        Integer sum1 = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum1);

        Integer sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);
    }

    /**
     * 使用Optional
     * 为什么它返回一个 Optional<Integer> 呢？考虑流中没有任何元素的情况。 reduce 操作无
     * 法返回其和，因为它没有初始值。这就是为什么结果被包裹在一个 Optional 对象里，以表明和
     * 可能不存在。
     * @return 一个Optional<Integer>
     */
    private static Optional<Integer> sumWithOption() {
        return numbers.stream().reduce(Integer::sum);
    }

    /**
     * 最小值的Optional的对象 包裹在Optional对象中
     * @return Optional对象 最小值
     */
    private static Optional<Integer> min() {
        return numbers.stream().reduce(Integer::min);
    }

    /**
     * 最大值Optional的对象 包裹在Optional对象中
     * @return Optional对象 最大值
     */
    private static Optional<Integer> max() {
        return numbers.stream().reduce(Integer::max);
    }

    /**
     * 使用orElse 进行返回 不用检查是否存在
     *
     * 返回最大值
     * @return 最大值
     */
    private static Integer maxNumber() {
       return numbers.stream().reduce(Integer::max).orElse(-1);
    }

    /**
     * 使用orElse 进行返回 不用检查是否存在
     *
     * 返回最小值
     * @return 最小值
     */
    private static Integer minNumber() {
        return numbers.stream().reduce(Integer::min).orElse(-1);
    }






}
