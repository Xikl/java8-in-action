package com.ximo.java8.chap3.interfaces;

/**
 * @author 朱文赵
 * @date 2018/2/28 9:33
 * @description 实现两个接口的实现类
 */
public class ATestInterfaceImpl implements ATestInterface1, ATestInterface2 {

    /**
     * 默认方法
     */
    @Override
    public void defaultMethod() {
        ATestInterface1.super.defaultMethod();
    }

    /**
     * 常规方法
     */
    @Override
    public void commonMethod() {
        System.out.println("实现类的方法");
    }

    /**
     * 使用默认的静态方法
     */
    public void useStaticMethod() {
        ATestInterface1.staticMethod();
        ATestInterface2.staticMethod();
    }
}
