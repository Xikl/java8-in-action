package com.ximo.java8.chap14;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author xikl
 * @date 2018/12/12
 */
public class LazyLists {


    private interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        MyList<T> filter(Predicate<T> predicate);

    }

    private class MyLinkedList<T> implements MyList<T> {

        private final T head;

        private final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public MyList<T> filter(Predicate<T> predicate) {
            return isEmpty() ? this : predicate.test(head()) ? new MyLinkedList<>(head, tail().filter(predicate)) : tail().filter(predicate);
        }
    }


    private class Empty<T> implements MyList<T> {

        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> filter(Predicate<T> predicate) {
            return this;
        }
    }

    private static class LazyList<T> implements MyList<T> {

        private final T head;

        private final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail.get();
        }


        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public MyList<T> filter(Predicate<T> predicate) {
            return isEmpty() ? this : predicate.test(head()) ?
                    new LazyList<>(head(), () -> tail().filter(predicate)) : tail().filter(predicate);
        }
    }


    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        // 头元素
        numbers.head();

        // 头元素的下一个的头部
        numbers.tail().head();

        // 头元素的下一个下一个的 头部
        numbers.tail().tail().head();
    }

}
