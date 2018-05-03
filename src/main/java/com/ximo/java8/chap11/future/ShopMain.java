package com.ximo.java8.chap11.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author 朱文赵
 * @date 2018/4/27 9:34
 * @description
 */
@Slf4j
public class ShopMain {

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        log.info("调用时间：{}s", invocationTime);
        doSomething();
        try {
            //可以设置超时时间
            Double price = futurePrice.get();
            System.out.printf("price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        log.info("价格调用 计算完成时间：{}s", retrievalTime);

    }


    /**
     * 一些别的操作
     */
    private static void doSomething() {

    }
}
