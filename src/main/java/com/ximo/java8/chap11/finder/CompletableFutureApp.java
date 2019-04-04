package com.ximo.java8.chap11.finder;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xikl
 * @date 2019/3/25
 */
public class CompletableFutureApp {

    public void whenComplete() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        completableFuture.whenComplete((result, err) -> System.out.println(result));

        IntStream.rangeClosed(0, 10).boxed().map(integer -> (Callable<Integer>) CompletableFutureApp::get).collect(Collectors.toList());
    }

    public static int get() {
        return 2;
    }

}
