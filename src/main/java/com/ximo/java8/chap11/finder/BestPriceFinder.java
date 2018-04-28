package com.ximo.java8.chap11.finder;

import com.ximo.java8.chap11.future.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author 朱文赵
 * @date 2018/4/27 12:30
 * @description 最好的价格查找器
 */
public class BestPriceFinder {

    private static final List<Shop> SHOPS = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    /**
     * 同步操作
     * 大概花费 4秒左右
     *
     * @param product
     * @return
     */
    public List<String> findPrices(String product) {
        return SHOPS.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
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

    public List<CompletableFuture<String>> findPriceByCompletedFuture(String product) {
        return SHOPS.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> format(shop, product)))
                .collect(Collectors.toList());
    }

    public String format(Shop shop, String product) {
        return String.format("%s price is %.2f", shop.getName(), shop.getPrice(product));
    }

}
