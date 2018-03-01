package com.ximo.java8.chap5;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 * @author 朱文赵
 * @date 2018/3/1 17:01
 * @description
 */
public class MyIntSupplier {

    private static IntSupplier intSupplier = new IntSupplier() {

        private int previous = 0;
        private int current = 1;

        @Override
        public int getAsInt() {
            int oldPrevious = this.previous;
            int nextValue = this.previous + this.current;
            this.previous = this.current;
            this.current = nextValue;
            return oldPrevious;
        }
    };

    private static void gengerate() {
        IntStream.generate(intSupplier)
                .limit(10)
                .forEach(System.out::print);
    }


}
