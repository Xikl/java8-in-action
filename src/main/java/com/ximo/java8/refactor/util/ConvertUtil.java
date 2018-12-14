package com.ximo.java8.refactor.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/4/24 13:37
 * @description
 */
@Slf4j
public class ConvertUtil {

    public static <T> T combine(T source, T target) {
        Class sourceClass = source.getClass();
        Class targetClass = target.getClass();
        if (!sourceClass.equals(targetClass)) {
            throw new IllegalArgumentException("target's class must be equal to source's");
        }
        Field[] fields = sourceClass.getDeclaredFields();
        for (Field field : fields) {
            handleCombine(source, target, field);
        }
        return target;
    }

    private static <T> void handleCombine(T source, T target, Field field) {
        field.setAccessible(true);
        try {
            if( !(field.get(source) == null) &&  !"serialVersionUID".equals(field.getName())){
                field.set(target,field.get(source));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("反射出现错误");
        }
    }

}
