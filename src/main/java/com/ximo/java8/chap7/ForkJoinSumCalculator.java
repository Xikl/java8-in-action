package com.ximo.java8.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author 朱文赵
 * @date 2018/3/8 10:42
 * @description
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    /** 需要求和的数组*/
    private final long[] numbers;
    /** 子任务处理的时候起始位置*/
    private final int start;
    /** 子任务处理的时候结束位置*/
    private final int end;

    /** 子任务不再分隔的门槛*/
    private static final long THRESHOLD = 10_000;

    /** 私有构造器 用于以递归的方式创建子任务*/
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            return computeSequentially();
        }
        //求和前一半
        int middle = length / 2;
//        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + middle);
        // 利用另一个ForkJoinPool线程异步执行新创建的子任务创建的子任务
        leftTask.fork();
        //求和后一半
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + middle, end);
        //同步执行第二个子任务 并且有可能进行进一步的划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果 如果没有那么就等待完成
        Long leftResult = leftTask.join();
        //        System.out.println(result);
        return leftResult + rightResult;
    }

    /**
     * 顺序计算结果值
     * @return sum值
     */
    private long computeSequentially() {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
//        System.out.println(String.format("compute %d~%d = %d", start, end, sum));
        return sum;
    }

    public static void main(String[] args) {
        long n = 20_000_000;
        long startTime = System.nanoTime();
        System.out.println(forkJoinSum(n));
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000);
    }

    /**
     * 调用ForkJoin 框架
     * @param n 数字边界
     * @return 1到n的和
     */
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
