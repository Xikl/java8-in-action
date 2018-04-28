package com.ximo.java8.chap11.future;

import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author 朱文赵
 * @date 2018/4/27 12:15
 * @description
 */
public class AsyncShop {

    private final String name;

    private final Random random;

    public AsyncShop(String name, Random random) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        AsyncUtil.delay();
        if (StringUtils.isEmpty(product)) {
            throw new RuntimeException("product not available");
        }
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
