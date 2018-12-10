package com.ximo.java8.chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xikl
 * @date 2018/12/10
 */
public class SubsetsMain {

    public static void main(String[] args) {
        List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
        subs.forEach(System.out::println);
    }


    /**
     * 求一个集合的所有自己和
     *
     * @param list 列表信息
     * @return
     */
    public static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }

    /**
     * 引用透明性
     *
     * @param list1 列表1
     * @param list2 列表2
     * @return 一个新的列表
     */
    private static List<List<Integer>> concat(List<List<Integer>> list1, List<List<Integer>> list2) {
        List<List<Integer>> result = new ArrayList<>(list1);
        result.addAll(list2);
        return result;
    }

    /**
     * 添加也是一样 保持不变性 确保不会修改原有的list
     *
     * @param first 第一个元素
     * @param lists 其余的元素
     * @return 将first添加到lists中
     */
    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }


}
