package com.ximo.java8.chap4.domain;

import com.ximo.java8.chap4.enums.Type;
import com.ximo.java8.chap6.enums.CaloricLevel;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/2/28 13:38
 * @description
 */
@Data
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final Integer calories;
    private final Type type;

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public static final List<Dish> MENU =
            Arrays.asList( new Dish("pork", false, 800, Type.MEAT),
                    new Dish("beef", false, 700, Type.MEAT),
                    new Dish("chicken", false, 400, Type.MEAT),
                    new Dish("french fries", true, 530, Type.OTHER),
                    new Dish("rice", true, 350, Type.OTHER),
                    new Dish("season fruit", true, 120, Type.OTHER),
                    new Dish("pizza", true, 550, Type.OTHER),
                    new Dish("prawns", false, 400, Type.FISH),
                    new Dish("salmon", false, 450, Type.FISH));

    public CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) {
            return CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        } else {
            return CaloricLevel.FAT;
        }
    }
}
