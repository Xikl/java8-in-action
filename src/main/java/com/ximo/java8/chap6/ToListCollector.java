package com.ximo.java8.chap6;

import com.ximo.java8.chap4.domain.Dish;
import com.ximo.java8.chap4.enums.Type;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collectors.toList;

/**
 * @author 朱文赵
 * @date 2018/3/5 9:50
 * @description
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    /**
     * A function that creates and returns a new mutable result container.
     *
     * @return a function which returns a new, mutable result container
     */
    @Override
    public Supplier<List<T>> supplier() {
        // () -> new ArrayList<>()
//        return new Supplier<List<T>>() {
//            @Override
//            public List<T> get() {
//                return new ArrayList<>();
//            }
//        }
        return ArrayList::new;
    }

    /**
     * A function that folds a value into a mutable result container.
     *
     * @return a function which folds a value into a mutable result container
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //(list, item) -> list.add(item)
        return List::add;
    }

    /**
     * A function that accepts two partial results and merges them.  The
     * combiner function may fold state from one argument into the other and
     * return that, or may return a new result container.
     *
     * @return a function which combines two partial results into a combined
     * result
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {list1.addAll(list2);
            return list1;};
    }

    /**
     * Perform the final transformation from the intermediate accumulation type
     * {@code A} to the final result type {@code R}.
     * <p>
     * <p>If the characteristic {@code IDENTITY_TRANSFORM} is
     * set, this function may be presumed to be an identity transform with an
     * unchecked cast from {@code A} to {@code R}.
     *
     * @return a function which transforms the intermediate result to the final
     * result
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        //返回自己
        return Function.identity();
    }

    /**
     * Returns a {@code Set} of {@code Collector.Characteristics} indicating
     * the characteristics of this Collector.  This set should be immutable.
     * IDENTITY_FINISH 不可变
     * CONCURRENT 并行操作
     *
     * @return an immutable set of collector characteristics
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }

    /**
     * 测试 最基本的toList 和 自定义的 toListCollector的实现
     * @param dishes 菜肴
     */
    private static void collectDish(List<Dish> dishes) {
        List<String> collect1 = dishes.stream()
                .map(Dish::getName).collect(toList());

        List<Type> collect2 = dishes.stream()
                .map(Dish::getType).collect(new ToListCollector<>());

    }


}
