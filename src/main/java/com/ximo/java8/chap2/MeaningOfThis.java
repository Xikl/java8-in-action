package com.ximo.java8.chap2;

/**
 * @author 朱文赵
 * @date 2018/2/26 14:07
 * @description 匿名内部类 谜题
 */
public class MeaningOfThis {

    private final int value = 4;

    private static void doIt() {
        int value = 6;
        Runnable runnable = new Runnable() {
            final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        MeaningOfThis.doIt();//输出5
    }

}
