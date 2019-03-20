package com.ximo.java8.chap11.finder;

import com.ximo.java8.chap11.Discount;
import com.ximo.java8.chap11.ExchangeService;
import com.ximo.java8.chap11.future.Quote;
import com.ximo.java8.chap11.future.Shop;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

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
                .collect(toList());
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
                .collect(toList());

        //为什么不用上一种返回呢 是因为如果两次map 那么将会变成同步 无法达到异步的过程
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    /**
     * 使用线程池
     * 可以保证每一个商店都有一个线程为他工作
     *
     * @param product
     * @return
     */
    public List<String> findPriceByCompletedFutureAndExecutorService(String product) {
        List<CompletableFuture<String>> futures = SHOPS.stream()
                //使用线程池
                .map(shop -> CompletableFuture.supplyAsync(() -> format(shop, product), priceExecutorService))
                //等待所有异步操作结束
//                .map(CompletableFuture::join)
                .collect(toList());

        //为什么不用上一种返回呢 是因为如果两次map 那么将会变成同步 无法达到异步的过程
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
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
                .collect(toList());
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
    public List<String> findPriceByQuoteClass(String product) {
        return SHOPS.stream()
                .map(shop -> shop.getAndFormatPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());

    }

    /**
     * 使用异步任务进行获得future
     *
     * 名称中不带 Async
     * 的方法和它的前一个任务一样，在同一个线程中运行；而名称以 Async 结尾的方法会将后续的任
     * 务提交到一个线程池，所以每个任务是由不同的线程处理的。
     *
     * @param product 商品
     * @return 异步任务进行获得future
     */
    public List<String> findPriceByCompletedFutureMore(String product) {
        List<CompletableFuture<String>> pricesFutures = SHOPS.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getAndFormatPrice(product), priceExecutorService))
                // 因为它没有io操作 是cpu密集 所以直接thenApply操作
                .map(future -> future.thenApply(Quote::parse))
                // applyDiscount 是比较耗时的操作 所以需要开启异步进行
                // compose 将两个异步操作进行流水线操作 如 第一个操作完成时，将其结果作为参数传递给第二个操作。
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), priceExecutorService)))
                .collect(toList());
        return pricesFutures.stream().map(CompletableFuture::join).collect(toList());


    }

    /**
     * 将两个异步操作合并 然后进行某些操作
     * @see CompletableFuture#thenCombine(CompletionStage, BiFunction)
     *
     * @param product
     * @return
     */
    public List<String> findPricesInUSD(String product) {
        List<CompletableFuture<String>> priceFutures = SHOPS.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                        .thenCombine(CompletableFuture
                                        .supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                                (price, rate) -> price * rate)
                        .thenApply(price -> shop.getName() + " price is " + price))
                .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }


}
