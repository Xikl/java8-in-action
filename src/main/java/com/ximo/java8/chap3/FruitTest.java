package com.ximo.java8.chap3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 朱文赵
 * @date 2018/2/27 17:13
 * @description
 */
public class FruitTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Fruit {
        private Integer weight;
        private String color;

        public Fruit(Integer weight) {
            this.weight = weight;
        }
    }

    static class Orange extends Fruit{

        Orange(Integer weight, String color) {
            super(weight, color);
        }

        public Orange(Integer weight) {
            super(weight);
        }
    }

    static class Banana extends  Fruit {

        public Banana(Integer weight) {
            super(weight);
        }
    }

    private static Map<String, Function<Integer, Fruit>> fruitMap = new HashMap<>();

    static {
        fruitMap.put("orange", Orange::new);
        fruitMap.put("banana", Banana::new);
    }

    public Fruit giveMeFruit(String fruit, Integer weight) {
        return fruitMap.get(fruit.toLowerCase()).apply(weight);
    }

}
