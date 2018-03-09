package com.ximo.java8.chap7.domain;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author 朱文赵
 * @date 2018/3/9 15:14
 * @description
 */
public class WordCounterSpliterator implements Spliterator<Character>{

    /** 输入的字符串*/
    private final String string;
    /** 当前的char的位置*/
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * 处理当前字符
     * 如果还有字符串要进行处理 就返回true
     * @param action 消费者 调用accept方法
     * @return 是否有字符要进行处理
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    /**
     * 对任务分割，返回一个新的Spliterator迭代器
     *
     * @return
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int spiltPos = currentSize / 2 + currentChar; spiltPos < string.length(); spiltPos++) {
            if (Character.isWhitespace(string.charAt(spiltPos))) {
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, spiltPos));
                currentChar = spiltPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 用于估算还剩下多少个元素需要遍历
     * @return
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
