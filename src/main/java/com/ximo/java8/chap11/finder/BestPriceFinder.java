package com.ximo.java8.chap11.finder;

import com.ximo.java8.chap11.Discount;
import com.ximo.java8.chap11.future.Quote;
import com.ximo.java8.chap11.future.Shop;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author 朱文赵
 * @date 2018/4/27 12:30
 * @description 最好的价格查找器
 */
public class BestPriceFinder {

    /** 初始化商店 */
    private static final List<Shop> SHOPS = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

//    private final Executor executor = Executors.newFixedThreadPool(Math.min(SHOPS.size(), 100), r -> {
//        Thread thread = new Thread(r);
//        thread.setDaemon(true);
//        return thread;
//    });

    /** 初始化线程池 */
    private final ScheduledExecutorService priceExecutorService = new ScheduledThreadPoolExecutor(Math.min(SHOPS.size(), 100),
            new BasicThreadFactory.Builder().namingPattern("price-schedule-pool-%d").daemon(true).build());

    /**
     * 同步操作
     * 大概花费 4秒左右
     *
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        return SHOPS.stream()
                .map(shop -> format(shop, product))
                .collect(Collectors.toList());
    }

    /**
     * 通过CompletedFuture 拿到返回值
     *
     * @param product
     * @return
     */
    public List<String> findPricesByCompletedFuture(String product) {
        return findPriceByCompletedFuture(product);
    }

    /**
     * 采用CompletableFuture的join方法
     * join 不会抛出任何检测到的异常
     * 两秒的速度 不是很推荐 让我们继续寻找一个好的方法
     *
     * @param product
     * @return
     */
    public List<String> findPriceByCompletedFuture(String product) {
        List<CompletableFuture<String>> futures = SHOPS.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> format(shop, product)))
                //等待所有异步操作结束
//                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        //为什么不用上一种返回呢 是因为如果两次map 那么将会变成同步 无法达到异步的过程
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 使用线程池
     * 可以保证每一个商店都有一个线程为他工作
     *
     * @param product
     * @return
     */
    public List<String> findPriceByCompletedFutureFactory(String product) {
        List<CompletableFuture<String>> futures = SHOPS.stream()
                //使用线程池
                .map(shop -> CompletableFuture.supplyAsync(() -> format(shop, product), priceExecutorService))
                //等待所有异步操作结束
//                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        //为什么不用上一种返回呢 是因为如果两次map 那么将会变成同步 无法达到异步的过程
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 并行操作
     * 大概花费1秒左右
     *
     * @param product
     * @return
     */
    public List<String> findPricesByParallel(String product) {
        return SHOPS.parallelStream()
                .map(shop -> format(shop, product))
                .collect(Collectors.toList());
    }


    public String format(Shop shop, String product) {
        return String.format("%s price is %.2f", shop.getName(), shop.getPrice(product));
    }

    /**
     * 打印出线程数
     */
    public void printThreadCounts() {
        int threadCounts = Runtime.getRuntime().availableProcessors();
        System.out.println(threadCounts);
    }

    /**
     * 找到price
     * 大概花费10秒左右
     *
     * @param product 商品
     * @return 商店价格信息 列表
     */
    public List<String> findPrices2(String product) {
        return SHOPS.stream()
                .map(shop -> shop.getPrice2(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());

    }


}
