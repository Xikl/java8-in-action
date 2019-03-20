package com.ximo.java8.chap11.future;

import com.ximo.java8.chap11.Discount;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author 朱文赵
 * @date 2018/4/27 9:00
 * @description
 */
public class Shop {

    private final String name;
    private final Random random;

    /**
     * 初始化
     */
    public Shop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    /**
     * 根据商品名字过得价格
     *
     * @param product 商品名字
     * @return 商品价格
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 格式化价格 按照商店名称 价格 折扣返回
     * 如：BestPrice:123.26:GOLD
     * 非异步的
     * @param product 商品名称
     * @return BestPrice:123.26:GOLD
     */
    public String getAndFormatPrice(String product) {
        double price = calculatePrice(product);
        // discount 中的 随机一个code
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    /**
     * 计算价格
     *
     * @param product 产品名字
     * @return 商品价格价格
     */
    private double calculatePrice(String product) {
        AsyncUtil.delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 异步计算商品价格
     * 等同于 CompletableFuture.supplyAsync(() -> calculatePrice(product));
     *
     * @param product 商品
     * @return 商品价格
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                //如果失败 就抛出这个异常
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public String getName() {
        return name;
    }
}
