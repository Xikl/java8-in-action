package com.ximo.java8.chap6;

import java.util.Arrays;

/**
 * @author 朱文赵
 * @date 2018/3/6 13:26
 * @description
 */
public class Regexp {

    /**
     * 匹配字符串中的“|”
     */
    private static void test() {
        // 定义字符串
        String info = "LXH:98|MLDN:90|LI:100";
        String[] s = info.split("\\|");
        Arrays.stream(s).forEach(System.out::println);
    }


    public static void main(String[] args) {
        test();
    }


}
