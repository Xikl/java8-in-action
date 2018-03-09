package com.ximo.java8.chap7.domain;

import lombok.Getter;

/**
 * @author 朱文赵
 * @date 2018/3/9 14:42
 * @description
 */
@Getter
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character character) {
        if (Character.isWhitespace(character)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    /**
     * 结合律 将两个 wordCounter计数器的Counter相加 不需要关心lastSpace
     *
     * @param wordCounter 计数器
     * @return 两个计数器的counter
     */
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

}
