package com.ximo.java8.chap13;

import java.util.stream.LongStream;

/**
 * @author xikl
 * @date 2018/12/11
 */
public class Recursion {

    public static long factorialIterative(long n) {
        long result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : factorialIterative(n - 1);
    }

    public static long factorialStreams(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (left, right) -> left * right);
    }

    // 尾部递归优化 减少局部变量表 栈帧数量

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);

    }

    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }



}
