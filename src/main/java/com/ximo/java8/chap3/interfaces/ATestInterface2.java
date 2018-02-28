package com.ximo.java8.chap3.interfaces;

/**
 * @author 朱文赵
 * @date 2018/2/28 9:29
 * @description
 */
public interface ATestInterface2 {

    /**
     * 静态方法
     */
    static void staticMethod() {
        System.out.println("ATestInterface2的静态方法");
    }

    /**
     * 默认方法
     */
    default void defaultMethod() {
        System.out.println("ATestInterface2的默认方法");
    }

    /** 常规方法*/
    void commonMethod();
}
