package com.ximo.java8.chap8;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 * @author 朱文赵
 * @date 2018/3/28 17:44
 * @description
 */
public class BinaryOperatorTest {

    public static void main(String[] args) {
        //原始opr
        BinaryOperator<Integer> binaryOperator = (a, b) -> (a + b);

        //调用add方法开始
        BinaryOperatorTest.operate(2, 3, (a, b) -> a + b);
        BinaryOperatorTest.operate(2, 3, (a, b) -> a - b);


    }

    public static Integer operate(Integer a, Integer b, IntBinaryOperator intBinaryOperator) {
        return intBinaryOperator.applyAsInt(a, b);
    }

}
