package com.ximo.java8.chap3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author 朱文赵
 * @date 2018/2/27 14:56
 * @description 排序
 */
public class Sorting {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>(Arrays.asList(new Apple(80, "green"),
                new Apple(155, "red"), new Apple(120, "red")));
        apples.sort(new AppleComparator());
        System.out.println(apples);

        //改变ArrayList第二个的值
        apples.set(1, new Apple(30, "green"));
        System.out.println(apples);

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }

        });

        apples.set(1, new Apple(20, "red"));
        apples.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));

        /* 逆序排序*/
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());

        /* 逆序排序 加 thenComparing*/
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));


    }

    /**
     * 谓词组合
     */
    public void youKnowTheMeaning() {
        /* 谓词组合*/
        /* 红苹果*/
        Predicate<Apple> redApple = a -> "red".equals(a.getColor());
        /* 不是 红苹果*/
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150)
                .or(a -> "green".equals(a.getColor()));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Apple {
        private Integer weight;
        private String color;

        Apple(Integer weight) {
            this.weight = weight;
        }
    }

    /**
     * 自定义苹果比较类
     */
    static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }

    }


    /**
     * 采用多种方式生成Apple
     */
    public void supplierApple() {
        /* 调用Apple中的无参构造*/
        Supplier<Apple> appleSupplier = Apple::new;
        appleSupplier.get();

        /* 调用 Apple中的关于重量的构造函数 */
        Function<Integer, Apple> integerAppleFunction = Apple::new;
        integerAppleFunction.apply(100);

        /*批量添加weight 然后返回具有重量Weight的Apple对象*/
        map(Arrays.asList(19, 22, 30), Apple::new).forEach(System.out::print);
        new ArrayList<>(Arrays.asList(19, 22, 30)).stream().map(Apple::new).forEach(System.out::print);

        BiFunction<Integer, String, Apple> biFunction = Apple::new;
        biFunction.apply(20, "red");

        /*Map -> Apple*/
        Map<Integer, String> map = new HashMap<>(3);
        map.put(30, "red");
        map.put(40, "green");
        map.put(50, "blue");
        map(map, Apple::new).forEach(System.out::print);
    }

    /**
     *
     * @param list
     * @param function
     * @return
     */
    private static List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(function.apply(e));
        }
        return result;
    }

    /**
     * map 转化为new Apple对象 然后收集为list
     *
     * @param map
     * @param biFunction
     * @return
     */
    private static List<Apple> map(Map<Integer, String> map, BiFunction<Integer, String, Apple> biFunction) {
        List<Apple> result = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            result.add(biFunction.apply(entry.getKey(), entry.getValue()));
        }
        return result;
    }

}
