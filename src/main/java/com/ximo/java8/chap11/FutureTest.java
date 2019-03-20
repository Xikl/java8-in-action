package com.ximo.java8.chap11;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author 朱文赵
 * @date 2018/4/24 19:43
 * @description
 */
public class FutureTest {

    /**
     * future类学习 callable
     */
    public void futureFirst() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Double> future = executorService.submit(FutureTest::doSomeLongComputation);
        try {
            //获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // 计算抛出一个异常
            //当前线程被阻断异常
            //超时异常
            e.printStackTrace();
        }
        doSomething();
    }

    /**
     * 一些需要长时间操作的方法
     *
     * @return 一个双精度浮点数
     */
    private static Double doSomeLongComputation() {
        return null;
    }


    /**
     * 一些其他的方法
     */
    private void doSomething() {

    }


    public static void main(String[] args) {
        IntStream.rangeClosed(0, 3).forEach(System.out::println);
    }

}
