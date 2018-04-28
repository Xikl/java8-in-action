package com.ximo.java8.chap11.future;

/**
 * @author 朱文赵
 * @date 2018/4/27 12:19
 * @description
 */
public class AsyncUtil {

    /**
     * 模拟延迟 通常为查询数据库等操作
     */
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
