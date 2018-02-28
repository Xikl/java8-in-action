package com.ximo.java8.chap5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/2/28 15:58
 * @description
 */
public class NumberStream {

    private static List<Integer> number = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    private static List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    private static List<Integer> numbers2 = new ArrayList<>(Arrays.asList(6,7,8));

    /**
     * 返回一个数组的
     * @return
     */
    private static List<Integer> square() {
        return number.stream()
                .map(n -> n * n)
                .collect(toList());
    }

    /**
     * 组个两个数字
     * @return
     */
    private static List<int[]> unionNumber() {
        return numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList());
    }

    /**
     * 组合只能被3整除的数字
     * @return 只能被3整除的数字列表
     */
    private static List<int[]> union3Number() {
        return numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(toList());
    }

    public static void main(String[] args) {
        square().forEach(System.out::print);
        System.out.println("\n");
        List<int[]> ints = unionNumber();
        //愚蠢的方法 哈哈哈哈 连数组可以通过下标获得都不知道 还以为自己的是对的
        for (int[] anInt : ints) {
            int index = 1;
            for (int i : anInt) {
                if (index % 2 == 0) {
                    System.out.print(i + "\t\t");
                }else{
                    System.out.print(i + ",");
                }
                index++;
            }
        }

        union3Number().forEach(pair -> System.out.print("(" + pair[0] + ", " + pair[1] + ")") );
    }


}
