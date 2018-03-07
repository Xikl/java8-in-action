package com.ximo.java8.chap7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author 朱文赵
 * @date 2018/3/6 18:29
 * @description 并行流
 */
public class ParallelStreams {

    /**
     * 第二快
     *
     * java7 版本
     * @param n 数字最大范围
     * @return 求和
     */
    private static long iterativeSum(int n) {
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += i;
        }
        return result;
    }

    /**
     *
     * 第二慢
     *
     * 流式操作 求和
     * @param n 数字最大边界
     * @return 求和
     */
    private static long sequentialSum(int n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     *
     * 最慢
     *
     * 并行求和
     * @param n 数字最大边界
     * @return 求和
     */
    private static long parallelSum(int n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     *
     * 最快
     *
     * 并行使用rangedClosed
     * @param n 数字边界
     * @return 求和
     */
    private static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0, Long::sum);
    }


    /**
     * 测试时间
     * @param adder function 对象
     * @param n 数字边界
     * @return 最快时间
     */
    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("sum:" + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    /**
     * error 不是原子操作 会产生静态条件
     * 关键在parallel
     *
     *
     * @param n 数字边界
     * @return 采用并行操作会产生静态条件
     */
    private static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


}
