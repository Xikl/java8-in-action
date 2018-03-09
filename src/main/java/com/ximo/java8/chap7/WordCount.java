package com.ximo.java8.chap7;

import com.ximo.java8.chap7.domain.WordCounter;
import com.ximo.java8.chap7.domain.WordCounterSpliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author 朱文赵
 * @date 2018/3/9 14:25
 * @description 统计字数
 */
public class WordCount {

    /** 初始化数据*/
    private static final String SENTENCE =
            " Nel mezzo del cammin di nostra vita  mi ritrovai in una selva oscura " +
                    " ché la dritta via era   smarrita ";

    /**
     * 统计单词数
     *
     * @param s 字符串
     * @return 单词个数
     */
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        //遍历字符串
        for (char c : s.toCharArray()) {
            //如果该char为空格
            if (Character.isWhitespace(c)) {
                //最近为空格 为 true
                lastSpace = true;
            } else {
                //如果上一字符个为空 且当前字符不是空格的时候
                if (lastSpace) {
                    //计数器加一
                    counter++;
                    //最近字符不为空
                    lastSpace = false;
                }
            }
        }
        return counter;
    }

    /**
     * 统计字数
     * @param s 字符串
     * @return 字符串单词数
     */
    public static int countWords(String s) {
        return countWords(getStream(s));
    }

    public static int countWordsBySpilterator(String s) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        return countWords(stream);
    }

    /**
     * 统计单词
     * @param stream 字节流
     * @return 单词个数
     */
    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
    /**
     * 获得流
     * @param s 字符串
     * @return 字节流
     */
    public static Stream<Character> getStream(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(s::charAt);
    }

    public static void main(String[] args) {
        System.out.println(countWordsBySpilterator(SENTENCE));
    }



}
