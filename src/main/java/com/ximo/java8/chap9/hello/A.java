package com.ximo.java8.chap9.hello;

/**
 * @author 朱文赵
 * @date 2018/4/19 19:26
 * @description
 */
public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }

}

interface B extends A {

    @Override
    default void hello() {
        System.out.println("Hello from B");
    }
}

class C extends D implements B, A {

    @Override
    public void hello() {
        System.out.println("本书的方法");
    }

    public static void main(String... args) {
        new C().hello();
    }
}

abstract class D implements A{

    @Override
    public abstract void hello();
}
