package com.ximo.java8.chap5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author 朱文赵
 * @date 2018/3/1 15:57
 * @description
 */
public class BuildingStreams {

    /**
     * 使用Stream.of来创建流
     */
    private static void of() {
        Stream<String> stream = Stream.of("Java 8", "Lambda", "Python", "Scala");
        stream.map(String::toUpperCase).forEach(System.out::print);
    }

    /**
     * 数组转化为流
     */
    private static void arrays2Stream() {
        int sum = Arrays.stream(new int[]{1, 2, 3, 4, 5}).sum();
    }

    /**
     * 创建文件流
     *
     * @return 文件流
     */
    private static long someIOStream() {
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            return lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 递归 生成 偶数 生成十条
     */
    private static void iterator() {
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::print);
    }

    /**
     * 递归 生成 菲波那切数列
     */
    private static void fayeBonaCheSeries() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println(t[0] + ", " + t[1]));
    }

    private static void printFayeBonaCheSeries() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    /**
     * generate
     */
    private static void generate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

}
