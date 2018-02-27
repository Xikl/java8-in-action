package com.ximo.java8.chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author 朱文赵
 * @date 2018/2/27 14:36
 * @description
 */
public class OtherNotes {


    /**
     * 特殊的void兼容规则
     * 如果一个Lambda的主体是一个语句表达式， 它就和一个返回 void 的函数描述符兼容（当
     然需要参数列表也兼容）。例如，以下两行都是合法的，尽管 List 的 add 方法返回了一个
     boolean ，而不是 Consumer 上下文（ T -> void ）所要求的 void ：
     */
    public static void specialVoid() {
        List<String> list = new ArrayList<>();
        Consumer<String> stringConsumer = list::add;
    }

    /**
     * 对局部变量的认识
     */
    public void localVariable() {
        int portNumber = 8080;
        Runnable a = () -> System.out.println(portNumber);
        a.run();
    }

    /**
     * 测试生产者
     */
    public static void testSupplier() {
        //每次调用返回一个数字
        Supplier<Integer> integerSupplier = () -> 42;
        Supplier<String> stringSupplier = () -> "Hello";
    }

}
