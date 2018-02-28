package com.ximo.java8.chap3.letter;

import java.util.function.Function;

/**
 * @author 朱文赵
 * @date 2018/2/28 10:23
 * @description 使用andThen
 */
public class Letter {

    private static String addHeader(String text) {
        return "来自朱文赵的一份信：" + text;
    }

    private static String addFooter(String text) {
        return text + "朱文赵";
    }

    private static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }

    public static void pipeline() {
        Function<String, String> addHeader = Letter::addHeader;
        addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
    }
}
